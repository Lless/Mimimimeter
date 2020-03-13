package com.github.lless.mimimimeter.controller;

import com.github.lless.mimimimeter.domain.Cat;
import com.github.lless.mimimimeter.service.CatService;
import com.github.lless.mimimimeter.utils.CookieConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatController {
    @Autowired
    CatService catService;

    @Autowired
    CookieConverter converter;

    @GetMapping("/vote")
    public String getCats(Model model,
                         @CookieValue(value = "voted", defaultValue = "") List<Long> votedCatsIds,
                         @CookieValue(value = "tochoose", defaultValue = "") List<Long> catsIds,
                         HttpServletResponse response) {
        List<Cat> cats;
        if (catsIds.isEmpty()) {
            cats = catService.getCats(votedCatsIds);
            var newCatsIds = cats.stream().map(Cat::getId).collect(Collectors.toList());
            response.addCookie(new Cookie("tochoose", converter.convert(newCatsIds)));
        } else {
            cats = catService.getCatsFromIds(catsIds);
        }
        if (cats.size() < 2) return "redirect:/results";
        model.addAttribute("cats", cats);
        return "vote";
    }

    @PostMapping(value = "/vote")
    public String vote(@RequestParam(name = "choose") Cat cat,
                       @CookieValue(value = "voted", defaultValue = "") List<Long> votedCatsIds,
                       @CookieValue(value = "tochoose") List<Long> catsIds,
                       HttpServletResponse response
    ){
        votedCatsIds.addAll(catsIds);
        catService.vote(cat);
        response.addCookie(new Cookie("voted", converter.convert(votedCatsIds)));
        response.addCookie(new Cookie("tochoose", ""));
        return "redirect:/vote";
    }

    @GetMapping("results")
    public String results(Model model) {
        model.addAttribute("winners", catService.getTop(10));
        return "results";
    }
}
