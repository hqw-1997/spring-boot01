package com.example.mapper;

import com.example.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question(title,description,gmt_create,gmt_modified,creator,comment_count,view_count,like_count,tag)VALUES(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{comment_count},#{view_count},#{like_count},#{tag})")
    void insertQuestion(Question question);
}
