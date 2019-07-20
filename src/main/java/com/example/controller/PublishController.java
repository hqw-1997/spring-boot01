package com.example.controller;

import com.example.mapper.QuestionMapper;
import com.example.mapper.UserMapper;
import com.example.model.Question;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String insertQuestion(
            @RequestParam("title")String title,
            @RequestParam("description") String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            Model model
    ){
        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if(user==null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        else if(title==null||title==""){
            model.addAttribute("error", "问题标题不能为空");
            return "publish";
        }
        else if(description==null||description==""){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        else if(tag==null||tag==""){
            model.addAttribute("error", "标签不能为空");
        }
        else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.insertQuestion(question);
            model.addAttribute("error", "发布成功");
        }
        return "publish";
    }
}
