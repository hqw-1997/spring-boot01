package com.example.mapper;

import com.example.model.Question;

public interface ExtendQuestionMapper {
    int addView_count(Question record);
    int addComment_count(Question record);
}
