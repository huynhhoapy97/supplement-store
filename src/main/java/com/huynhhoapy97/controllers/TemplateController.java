package com.huynhhoapy97.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("home-page")
    public String index() {
        return "template/home-page";
    }
}
