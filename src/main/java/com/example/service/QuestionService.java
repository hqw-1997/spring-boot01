package com.example.service;


import com.example.dto.QuestionDto;
import com.example.dto.QuestionPageDto;
import com.example.exception.CustomException;
import com.example.exception.ECustomException;
import com.example.mapper.ExtendQuestionMapper;
import com.example.mapper.QuestionMapper;
import com.example.mapper.UserMapper;
import com.example.model.Question;
import com.example.model.QuestionExample;
import com.example.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    ExtendQuestionMapper extendQuestionMapper;


    public QuestionPageDto viewQuestion(Integer page, Integer size){
        Integer titlePage=6*(page-1);
   //     page-=1;

        Integer titleCount =(int) questionMapper.countByExample(new QuestionExample());

        QuestionExample questionExample=new QuestionExample();
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(titlePage, size));
        List<QuestionDto> questionDtos=new ArrayList<>();
        QuestionPageDto questionPageDto=new QuestionPageDto();
        for (Question question : questionList) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
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
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(iuser.getId());
        Integer titleCount= (int)questionMapper.countByExample(questionExample);
        QuestionExample questionExample1=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(iuser.getId());
        List<Question> questionList=questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(page,size));

        List<QuestionDto> questionDtos=new ArrayList<>();
        QuestionPageDto questionPageDto=new QuestionPageDto();
        for (Question question : questionList) {
            User user=userMapper.selectByPrimaryKey(question.getCreator());
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
        Question question=questionMapper.selectByPrimaryKey(id);

        if(question==null){
            throw new CustomException(ECustomException.QUESTION_NOT_FOUND);
        }
        long sd=question.getGmt_create();
        Date dat=new Date(sd);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String sb=format.format(gc.getTime());
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setDate(sb);
        User user=userMapper.selectByPrimaryKey(questionDto.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }


    public void insertOrUpdate(Question question){
        if(question.getId()==null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.insert(question);
        }else{
            Question dbquestion=new Question();
            dbquestion.setGmt_modified(System.currentTimeMillis());
            dbquestion.setTitle(question.getTitle());
            dbquestion.setDescription(question.getDescription());
            dbquestion.setTag(question.getTag());
            QuestionExample questionExample =new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(dbquestion,questionExample );

        }

    }

    //增加阅览数
    public void addView_count(Integer id){
        Question question=new Question();
        question.setId(id);
        question.setView_count(1);
        extendQuestionMapper.addView_count(question);
    }
    }


