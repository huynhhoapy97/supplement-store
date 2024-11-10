package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.models.Account;
import com.huynhhoapy97.services.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("login")
    public String login(@ModelAttribute("account") Account account, ModelMap modelMap) {
        account = accountService.verifyUserName(account);
        if (account == null) {
            proceedAccountNotExists(modelMap);
        } else {
            proceedAccountExists(modelMap, account);
        }

        return "admin/home-page";
    }

    @PostMapping("change-password")
    public String changePassword(@ModelAttribute("account") Account account, ModelMap modelMap) {
        boolean isSatisfied = accountService.checkPasswordValidity(account);
        if (!isSatisfied) {
            proceedPasswordNotSatisfied(modelMap);
        } else {
            proceedPasswordSatisfied(modelMap, account);
        }

        return "admin/home-page";
    }

    @PostMapping("login-completion")
    public String loginCompletion(@ModelAttribute("account") Account account, ModelMap modelMap) {
        boolean isMatched = accountService.confirmPassword(account);
        if (!isMatched) {
            proceedPasswordNotMatched(modelMap, account);
        } else {
            proceedPasswordMatched(account);

            return "redirect:/admin/dashboard";
        }

        return "admin/home-page";
    }

    @GetMapping("logout")
    public String logout() {
        accountService.removeLoginSession();

        return "redirect:/admin/home-page";
    }

    private void proceedAccountNotExists(ModelMap modelMap) {
        modelMap.addAttribute("accountStatus", "Tên tài khoản không tồn tại");
        modelMap.addAttribute("pageName", "account/login.jsp");
        modelMap.addAttribute("account", new Account());
    }

    private void proceedAccountExists(ModelMap modelMap, Account account) {
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            modelMap.addAttribute("pageName", "account/change-password.jsp");
            modelMap.addAttribute("account", account);
        } else {
            refreshPassword(account);
            modelMap.addAttribute("pageName", "account/login-completion.jsp");
            modelMap.addAttribute("account", account);
        }
    }

    private void proceedPasswordNotSatisfied(ModelMap modelMap) {
        modelMap.addAttribute("accountStatus", "Kiểm tra lại thông tin mật khẩu");
        modelMap.addAttribute("pageName", "account/change-password.jsp");
    }

    private void proceedPasswordSatisfied(ModelMap modelMap, Account account) {
        boolean isSuccess = accountService.updatePassword(account);

        if (isSuccess) {
            modelMap.addAttribute("pageName", "account/login.jsp");
            modelMap.addAttribute("account", account);
        } else {
            modelMap.addAttribute("accountStatus", "Có lỗi xảy ra");
            modelMap.addAttribute("pageName", "account/change-password.jsp");
        }
    }

    private void proceedPasswordNotMatched(ModelMap modelMap, Account account) {
        modelMap.addAttribute("accountStatus", "Mật khẩu không chính xác");
        modelMap.addAttribute("pageName", "account/login-completion.jsp");
        modelMap.addAttribute("account", account);
    }

    private void proceedPasswordMatched(Account account) {
        accountService.saveLoginSession(account);
    }

    private void refreshPassword(Account account) {
        account.setPassword("");
    }
}
