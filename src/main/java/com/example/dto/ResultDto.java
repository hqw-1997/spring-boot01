package com.example.dto;

import com.example.exception.CustomException;
import com.example.exception.ECustomException;

public class ResultDto {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultDto errorof(Throwable e){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(((CustomException) e).getCode());
        resultDto.setMessage(e.getMessage());
        return resultDto;
    }

    public static ResultDto errorof(ECustomException e){
        ResultDto resultDto=new ResultDto();
        resultDto.setCode(e.getCode());
        resultDto.setMessage(e.getMessage());
        return resultDto;
    }
}
