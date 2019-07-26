package com.example.service;


import com.example.dto.QuestionDto;
import com.example.dto.QuestionPageDto;
import com.example.mapper.QuestionMapper;
import com.example.mapper.UserMapper;
import com.example.model.Question;
import com.example.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public QuestionPageDto viewQuestion(Integer page, Integer size){
        Integer titlePage=6*(page-1);
   //     page-=1;
        Integer titleCount=questionMapper.selectQuestion();
        List<Question> questionList=questionMapper.viewQuestion(titlePage,size);
        List<QuestionDto> questionDtos=new ArrayList<>();
        QuestionPageDto questionPageDto=new QuestionPageDto();
        for (Question question : questionList) {
            User user=userMapper.findUserById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);

            long sd=question.getGmt_create();
            Date dat=new Date(sd);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dat);
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
            String sb=format.format(gc.getTime());
            questionDto.setDate(sb);
            questionDtos.add(questionDto);
        }
        questionPageDto.setQuestionDtoList(questionDtos);
        questionPageDto.setQuestionPageDto(titleCount, page, size);
        return questionPageDto;
    }
    public QuestionPageDto viewQuestionById(Integer page, Integer size,User iuser){
        Integer titlePage=6*(page-1);
        //     page-=1;
        Integer titleCount=questionMapper.selectQuestionById(iuser.getId());

        List<Question> questionList=questionMapper.viewQuestionById(titlePage,size,iuser.getId());

        List<QuestionDto> questionDtos=new ArrayList<>();
        QuestionPageDto questionPageDto=new QuestionPageDto();
        for (Question question : questionList) {
            User user=userMapper.findUserById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtos.add(questionDto);

        }

        questionPageDto.setQuestionDtoList(questionDtos);
        questionPageDto.setQuestionPageDto(titleCount, page, size);
        return questionPageDto;
    }

    public QuestionDto findQuestionById(Integer id){
        QuestionDto questionDto=questionMapper.findQuestionById(id);


        long sd=questionDto.getGmt_create();
        Date dat=new Date(sd);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String sb=format.format(gc.getTime());
        questionDto.setDate(sb);
        User user=userMapper.findUserById(questionDto.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }
}
