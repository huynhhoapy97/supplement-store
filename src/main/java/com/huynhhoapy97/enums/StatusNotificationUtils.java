package com.huynhhoapy97.enums;

public enum StatusNotificationUtils {
    USERNAME_IS_NOT_EXISTS("Tên tài khoản không tồn tại"),
    PASSWORD_CHECK("Kiểm tra lại thông tin mật khẩu"),
    PASSWORD_IS_NOT_CORRECT("Mật khẩu không chính xác"),
    ERROR_WARNING("Có lỗi xảy ra"),
    DATA_MANIPULATION_SUCCESSFULLY("Success"),
    DATA_MANIPULATION_FAIL("Fail"),
    DATA_MANIPULATION_ERROR("Error");

    private final String name;

    public String getName() {
        return name;
    }

    StatusNotificationUtils(String name) {
        this.name = name;
    }
}
