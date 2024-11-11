package com.huynhhoapy97.controllers.admin;

import com.huynhhoapy97.enums.ModelMapUtils;
import com.huynhhoapy97.enums.StatusNotificationUtils;
import com.huynhhoapy97.enums.StringUtils;
import com.huynhhoapy97.enums.ViewPageUtils;
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

        return ViewPageUtils.ADMIN_HOMEPAGE.getName();
    }

    @PostMapping("change-password")
    public String changePassword(@ModelAttribute("account") Account account, ModelMap modelMap) {
        boolean isSatisfied = accountService.checkPasswordValidity(account);
        if (!isSatisfied) {
            proceedPasswordNotSatisfied(modelMap);
        } else {
            proceedPasswordSatisfied(modelMap, account);
        }

        return ViewPageUtils.ADMIN_HOMEPAGE.getName();
    }

    @PostMapping("login-completion")
    public String loginCompletion(@ModelAttribute("account") Account account, ModelMap modelMap) {
        boolean isMatched = accountService.confirmPassword(account);
        if (!isMatched) {
            proceedPasswordNotMatched(modelMap, account);
        } else {
            proceedPasswordMatched(account);

            return ViewPageUtils.REDIRECT_ADMIN_DASHBOARD.getName();
        }

        return ViewPageUtils.ADMIN_HOMEPAGE.getName();
    }

    @GetMapping("logout")
    public String logout() {
        accountService.removeLoginSession();

        return ViewPageUtils.REDIRECT_ADMIN_HOMEPAGE.getName();
    }

    private void proceedAccountNotExists(ModelMap modelMap) {
        modelMap.addAttribute(ModelMapUtils.ACCOUNT_STATUS.getName(),
                StatusNotificationUtils.USERNAME_IS_NOT_EXISTS.getName());
        modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                ViewPageUtils.ADMIN_ACCOUNT_LOGIN.getName());
        modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(),
                new Account());
    }

    private void proceedAccountExists(ModelMap modelMap, Account account) {
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                    ViewPageUtils.ADMIN_ACCOUNT_CHANGE_PASSWORD.getName());
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(),
                    account);
        } else {
            refreshPassword(account);
            modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                    ViewPageUtils.ADMIN_ACCOUNT_LOGIN_COMPLETION.getName());
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(),
                    account);
        }
    }

    private void proceedPasswordNotSatisfied(ModelMap modelMap) {
        modelMap.addAttribute(ModelMapUtils.ACCOUNT_STATUS.getName(),
                StatusNotificationUtils.PASSWORD_CHECK.getName());
        modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                ViewPageUtils.ADMIN_ACCOUNT_CHANGE_PASSWORD.getName());
    }

    private void proceedPasswordSatisfied(ModelMap modelMap, Account account) {
        boolean isSuccess = accountService.updatePassword(account);

        if (isSuccess) {
            modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                    ViewPageUtils.ADMIN_ACCOUNT_LOGIN.getName());
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(),
                    account);
        } else {
            modelMap.addAttribute(ModelMapUtils.ACCOUNT_STATUS.getName(),
                    StatusNotificationUtils.ERROR_WARNING.getName());
            modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                    ViewPageUtils.ADMIN_ACCOUNT_CHANGE_PASSWORD.getName());
        }
    }

    private void proceedPasswordNotMatched(ModelMap modelMap, Account account) {
        modelMap.addAttribute(ModelMapUtils.ACCOUNT_STATUS.getName(),
                StatusNotificationUtils.PASSWORD_IS_NOT_CORRECT.getName());
        modelMap.addAttribute(ModelMapUtils.PAGE_NAME.getName(),
                ViewPageUtils.ADMIN_ACCOUNT_LOGIN_COMPLETION.getName());
        modelMap.addAttribute(ModelMapUtils.ACCOUNT_INSTANCE.getName(),
                account);
    }

    private void proceedPasswordMatched(Account account) {
        accountService.saveLoginSession(account);
    }

    private void refreshPassword(Account account) {
        account.setPassword(StringUtils.EMPTY.getName());
    }
}
