package com.example.service;

import com.example.enums.CommentType;
import com.example.exception.CustomException;
import com.example.exception.ECustomException;
import com.example.mapper.CommentMapper;
import com.example.mapper.ExtendQuestionMapper;
import com.example.model.Comment;
import com.example.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class CommentServise {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ExtendQuestionMapper extendQuestionMapper;
    @Transactional
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
    }
}
