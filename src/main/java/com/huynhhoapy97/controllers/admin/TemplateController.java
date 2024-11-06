package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class TemplateController {
    @GetMapping("home-page")
    public String homePage(ModelMap modelMap) {
        modelMap.addAttribute("pageName", "account/login.jsp");
        modelMap.addAttribute("account", new Account());

        return "admin/home-page";
    }
}
