package com.github.lless.mimimimeter.controller;

import com.github.lless.mimimimeter.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatController {
    @Autowired
    CatService catService;

    @GetMapping("/choose")
    public String choose(Model model) {
        model.addAttribute("cats", catService.getCats());
        return "choose";
    }
}
