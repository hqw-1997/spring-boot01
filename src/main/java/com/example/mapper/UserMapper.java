package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name,account_id,token,gmt_create,gmt_modified)VALUES(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insertUser(User user);

}

