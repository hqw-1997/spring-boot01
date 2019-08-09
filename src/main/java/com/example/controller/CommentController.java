package com.example.controller;

import com.example.dto.CommentDto;
import com.example.dto.ResultDto;
import com.example.enums.CommentType;
import com.example.exception.CustomException;
import com.example.exception.ECustomException;
import com.example.model.Comment;
import com.example.model.User;
import com.example.service.CommentServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentServise commentServise;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object Comment(@RequestBody CommentDto comment,
                          HttpServletRequest request){
        User sessionUser = (User) request.getSession().getAttribute("user");
        if(sessionUser==null){
            throw new  CustomException(ECustomException.USER_NOT_LOGIN);
        }
        User user=(User) request.getSession().getAttribute("user");
        int commentor =user.getId();
        Comment dbcomment=new Comment();
        dbcomment.setCommmentator(commentor);
        dbcomment.setContent(comment.getContent());
        dbcomment.setGmt_create(System.currentTimeMillis());
        dbcomment.setGmt_modified(dbcomment.getGmt_create());
        dbcomment.setLike_count(0l);
        dbcomment.setParent_id(comment.getParent_id());
        dbcomment.setType(comment.getType());
        commentServise.commentInsert(dbcomment);
        return null;
    }

    //返回二级评论内容
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto comment(@PathVariable("id")int id){
        commentServise.viewComment(id, CommentType.COMMENT_COMMENT.getType());
        return null;
    }
}
