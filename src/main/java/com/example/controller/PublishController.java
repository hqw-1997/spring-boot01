package com.example.controller;

import com.example.dto.QuestionDto;
import com.example.mapper.QuestionMapper;
import com.example.mapper.UserMapper;
import com.example.model.Question;
import com.example.model.User;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private QuestionService questionService;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam(value = "title")String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "tag")String tag,
            @RequestParam(value = "id")int id,
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
            question.setId(id);
            questionService.insertOrUpdate(question);
            model.addAttribute("error", "发布成功");
        }
        return "publish";
    }
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") int id,
                                  Model model){
        Question questionDto = questionMapper.selectByPrimaryKey(id);
        questionMapper.selectByPrimaryKey(id);
        model.addAttribute("title", questionDto.getTitle());
        model.addAttribute("description", questionDto.getDescription());
        model.addAttribute("tag", questionDto.getTag());
        model.addAttribute("id", questionDto.getId());
        return "publish";
    }

}
