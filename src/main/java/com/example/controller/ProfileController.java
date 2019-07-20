package com.example.controller;

import com.example.dto.QuestionPageDto;
import com.example.model.User;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profileController(
            @PathVariable(name="action")String action,
            @RequestParam(name="page",defaultValue = "1")Integer page,
            @RequestParam(name="size",defaultValue = "6")Integer size,
            HttpServletRequest request,
            Model model
    ){

        User user=(User) request.getSession().getAttribute("user");

        if("question".equals(action)){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName", "我的问题");

            QuestionPageDto questionPageDto= questionService.viewQuestionById(page,size,user);
            model.addAttribute("questionPageDto", questionPageDto);
        }
        if("replay".equals(action)){
            model.addAttribute("section", "replay");
            model.addAttribute("sectionName", "最新回复");
        }
        return "profile";
    }
}
