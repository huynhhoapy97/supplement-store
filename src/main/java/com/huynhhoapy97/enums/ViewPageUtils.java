package com.huynhhoapy97.enums;

public enum ViewPageUtils {
    ADMIN_HOMEPAGE("admin/home-page"),
    ADMIN_DASHBOARD("admin/dashboard"),
    REDIRECT_ADMIN_HOMEPAGE("redirect:/admin/home-page"),
    REDIRECT_ADMIN_DASHBOARD("redirect:/admin/dashboard"),
    ADMIN_ACCOUNT_LOGIN("account/login.jsp"),
    ADMIN_ACCOUNT_CHANGE_PASSWORD("account/change-password.jsp"),
    ADMIN_ACCOUNT_LOGIN_COMPLETION("account/login-completion.jsp");

    private final String name;

    public String getName() {
        return name;
    }

    ViewPageUtils(String name) {
        this.name = name;
    }
}
