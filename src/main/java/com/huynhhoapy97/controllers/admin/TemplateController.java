package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.enums.ModelMapUtils;
import com.huynhhoapy97.enums.ViewPageUtils;
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
            return ViewPageUtils.REDIRECT_ADMIN_DASHBOARD.getName();
        } else {
            modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(), ViewPageUtils.ADMIN_ACCOUNT_LOGIN.getName());
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(), new Account());

            return ViewPageUtils.ADMIN_HOMEPAGE.getName();
        }
    }

    @GetMapping("dashboard")
    public String dashboard(ModelMap modelMap) {
        boolean isExisting = accountService.checkLoginSessionExisting();

        if (isExisting) {
            Account account = accountService.getLoginInformation();
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(), account);

            return ViewPageUtils.ADMIN_DASHBOARD.getName();
        } else {
            return ViewPageUtils.REDIRECT_ADMIN_HOMEPAGE.getName();
        }
    }
}
