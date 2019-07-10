package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("INSERT INTO user (name, account_id,token,gmt_create,gmt_modified )VALUES(#{name}，#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    public void insertUser(User user);

}

