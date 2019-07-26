package com.example.mapper;

import com.example.dto.QuestionDto;
import com.example.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)VALUES(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count},#{like_count},#{tag})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{page},#{size}")
    List<Question> viewQuestion(@Param("page") int page,@Param("size") int size);

    @Select("SELECT COUNT(*) FROM question ")
    Integer selectQuestion();

    @Select("SELECT COUNT(*) FROM question where creator=#{id}")
    Integer selectQuestionById(@Param("id")int id);

    @Select("select * from question where creator=#{id} limit #{page},#{size}")
    List<Question> viewQuestionById(@Param("page") int page,@Param("size") int size,@Param("id")int id);

    @Select("select * from question where id=#{id}")
    QuestionDto findQuestionById(@Param("id")Integer id);
}
