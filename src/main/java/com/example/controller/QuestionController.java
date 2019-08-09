package com.example.controller;

import com.example.dto.QuestionDto;
import com.example.dto.ResponseCommentDto;
import com.example.enums.CommentType;
import com.example.service.CommentServise;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentServise commentServise;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id,
                           Model model){
        QuestionDto questionDto=questionService.findQuestionById(id);
        questionService.addView_count(id);
        List<ResponseCommentDto> responseCommentDtos = commentServise.viewComment(id, CommentType.QUESTION_COMMENT.getType());
        model.addAttribute("question", questionDto);
        model.addAttribute("comment", responseCommentDtos);
        return "question";

    }
}
