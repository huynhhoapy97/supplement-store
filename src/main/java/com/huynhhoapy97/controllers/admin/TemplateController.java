package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.models.Account;
import com.huynhhoapy97.services.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class TemplateController {
    @Autowired
    private AccountService accountService;

    @GetMapping("home-page")
    public String homePage(ModelMap modelMap) {
        boolean isExisting = accountService.checkLoginSessionExisting();

        if (isExisting) {
            return "redirect:/admin/dashboard";
        } else {
            modelMap.addAttribute("pageName", "account/login.jsp");
            modelMap.addAttribute("account", new Account());

            return "admin/home-page";
        }
    }

    @GetMapping("dashboard")
    public String dashboard(ModelMap modelMap) {
        boolean isExisting = accountService.checkLoginSessionExisting();

        if (isExisting) {
            Account account = accountService.getLoginInformation();
            modelMap.addAttribute("account", account);

            return "admin/dashboard";
        } else {
            return "redirect:/admin/home-page";
        }
    }
}
