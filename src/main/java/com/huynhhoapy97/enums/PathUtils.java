package com.huynhhoapy97.enums;

public enum PathUtils {
    CATEGORY_UPLOADS("uploads/category/");

    private String name;

    public String getName() {
        return name;
    }

    PathUtils(String name) {
        this.name = name;
    }
}
