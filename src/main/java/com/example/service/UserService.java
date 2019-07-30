package com.example.service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void insertOrUpdate(User user){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccount_idEqualTo(user.getAccount_id());
        List<User> userList = userMapper.selectByExample(userExample);

        if(userList.size()==0){
            //插入
            user.setGmt_create(System.currentTimeMillis());//用当前毫秒数作为用户创建时间
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }else{
            //更新
            User userl=new User();
            userl=userList.get(0);
            user.setGmt_modified(System.currentTimeMillis());
            User dbuser=new User();
            dbuser.setPhoto(user.getPhoto());
            dbuser.setGmt_modified(user.getGmt_modified());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userExample.createCriteria().andIdEqualTo(userl.getId());
            userMapper.updateByExampleSelective(dbuser, userExample);

        }
    }
}
