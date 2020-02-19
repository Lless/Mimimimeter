package com.github.lless.mimimimeter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatController {
    @GetMapping("/choose")
    public String choose(Model model) {
        model.addAttribute("catName", "Вася");
        return "choose";
    }
}
