package com.example.mapper;

import com.example.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name,account_id,token,gmt_create,gmt_modified,photo)VALUES(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified},#{photo})")
    void insertUser(User user);

    @Select("SELECT * FROM user where token= #{token}")
    User findUserByToken(@Param("token") String token);

    @Select("SELECT * FROM user where id= #{id}")
    User findUserById(@Param("id") int id);

    @Select("SELECT * FROM user where Account_id= #{Account_id}")
    User findUserByAccount_id(@Param("Account_id") String Account_id);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmt_modified},photo=#{photo}")
    void updateUserByAccount_id(User user);
}

