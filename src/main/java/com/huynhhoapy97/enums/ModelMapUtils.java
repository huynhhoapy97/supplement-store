package com.huynhhoapy97.enums;

public enum ModelMapUtils {
    ACCOUNT_STATUS("accountStatus"),
    PAGE_NAME("pageName"),
    ACCOUNT_INSTANCE("account");

    private final String name;

    public String getName() {
        return name;
    }

    ModelMapUtils(String name) {
        this.name = name;
    }
}
