package com.example.advice;

import com.alibaba.fastjson.JSON;
import com.example.dto.ResultDto;
import com.example.exception.CustomException;
import com.example.exception.ECustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        if(request.getContentType().equals("application/json")){
            ResultDto resultDto;
            if (e instanceof CustomException) {
                resultDto=ResultDto.errorof(e);
            }else {
                resultDto=ResultDto.errorof(ECustomException.SYSTEM_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer=response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            }catch (Exception ioe){

            }
            return null;

        }else {if (e instanceof CustomException) {
            model.addAttribute("code", ((CustomException) e).getCode());
            model.addAttribute("message", e.getMessage());

        }else {
            model.addAttribute("message", ECustomException.SYSTEM_ERROR);
        }
            return new ModelAndView("error");
        }

    }


}