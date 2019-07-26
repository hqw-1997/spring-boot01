package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void updataOrInsert(User user){
        User dbuser=userMapper.findUserByAccount_id(user.getAccount_id());
        if(dbuser==null){
            //插入
            user.setGmt_create(System.currentTimeMillis());//用当前毫秒数作为用户创建时间
            user.setGmt_modified(user.getGmt_create());
            userMapper.insertUser(user);
        }else{
            //更新
            user.setGmt_modified(System.currentTimeMillis());
            userMapper.updateUserByAccount_id(user);

        }
    }
}
