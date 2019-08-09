package com.example.service;

import com.example.dto.ResponseCommentDto;
import com.example.enums.CommentType;
import com.example.exception.CustomException;
import com.example.exception.ECustomException;
import com.example.mapper.CommentMapper;
import com.example.mapper.ExtendQuestionMapper;
import com.example.mapper.UserMapper;
import com.example.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class CommentServise {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ExtendQuestionMapper extendQuestionMapper;

    @Autowired
    private UserMapper userMapper;
    //@Transactional
    public void commentInsert(Comment comment){
        if(comment.getParent_id()==null){
            throw new CustomException(ECustomException.TARGET_NOT_FOUNT);
        }
        if(CommentType.isOrNoExit(comment.getType())==false){
            throw  new CustomException(ECustomException.TYPE_ILLEGA);
        }
        if(comment.getContent()==null || comment.getContent()==""){
            throw new CustomException(ECustomException.CONTENT_IS_NULL);
        }
        if(comment.getType()==CommentType.QUESTION_COMMENT.getType()){
            commentMapper.insert(comment);
            Question question=new Question();
            question.setId(comment.getParent_id());
            question.setComment_count(1);
            extendQuestionMapper.addComment_count(question);
        }else {
            commentMapper.insert(comment);
        }
        throw new CustomException(ECustomException.SUCCESS);
    }
    public List<ResponseCommentDto> viewComment(int id,int type){
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andParent_idEqualTo(id).andTypeEqualTo(type);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //去重
        Set<Integer> collect = comments.stream().map(comment -> comment.getCommmentator()).collect(Collectors.toSet());
        List<Integer> userids=new ArrayList<>();
        userids.addAll(collect);
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdIn(userids);
        List<User> userList = userMapper.selectByExample(userExample);
        Map<Integer, User> collect1 = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<ResponseCommentDto> collect2 = comments.stream().map(comment -> {
            ResponseCommentDto responseCommentDto = new ResponseCommentDto();
            BeanUtils.copyProperties(comment, responseCommentDto);
            responseCommentDto.setUser(collect1.get(comment.getCommmentator()));
            return responseCommentDto;
        }).collect(Collectors.toList());

        return  collect2;
    }
}
