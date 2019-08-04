package com.example.exception;

public class CustomException extends RuntimeException{
    private String message;
    private int code;

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public CustomException(ICustomException icException) {
        this.code=icException.getCode();
        this.message =icException.getMessage();
    }
}
