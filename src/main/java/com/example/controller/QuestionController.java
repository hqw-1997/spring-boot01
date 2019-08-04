package com.example.controller;

import com.example.dto.QuestionDto;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id,
                           Model model){
        QuestionDto questionDto=questionService.findQuestionById(id);
        questionService.addView_count(id);
        model.addAttribute("question", questionDto);
        return "question";

    }
}
