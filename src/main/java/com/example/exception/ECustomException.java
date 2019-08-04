package com.example.exception;

public enum ECustomException implements ICustomException {
    QUESTION_NOT_FOUND(2001,"所选问题找不到"),
    USER_NOT_LOGIN(2002,"用户未登陆请先登陆"),
    TARGET_NOT_FOUNT(2003,"目标找不到" ),
    CONTENT_IS_NULL(2004,"内容为空"),
    TYPE_ILLEGA(2005,"类型错误"),
    SYSTEM_ERROR(2006,"系统错误"),
    SUCCESS(200,"成功");


    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    private int code;
    private String message;

    ECustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
