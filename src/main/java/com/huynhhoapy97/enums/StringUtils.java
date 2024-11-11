package com.huynhhoapy97.enums;

public enum StringUtils {
    EMPTY("");

    private final String name;

    public String getName() {
        return name;
    }

    StringUtils(String name) {
        this.name = name;
    }
}
