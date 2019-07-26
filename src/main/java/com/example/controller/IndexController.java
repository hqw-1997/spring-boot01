package com.example.controller;

import com.example.dto.QuestionPageDto;
import com.example.mapper.UserMapper;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(
            @RequestParam(name="page",defaultValue = "1")Integer page,
            @RequestParam(name="size",defaultValue = "6")Integer size,
            HttpServletRequest request,
    Model model){

        QuestionPageDto questionPageDto= questionService.viewQuestion(page,size);
   //     System.out.println(questionPageDto.getQuestionDtoList().);
        model.addAttribute("questionPageDto", questionPageDto);
        return "index";
    }
}
