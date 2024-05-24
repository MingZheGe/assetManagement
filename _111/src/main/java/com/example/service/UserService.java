package com.example.service;


import com.example.dao.UserEntityMapper;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;


    //登录
    public List<User> login (User user){
        List<User> result = userEntityMapper.login(user);
        return result;
    }

    //注册
    public int eroll (User user){
        int userResult = userEntityMapper.eroll(user);
        if(userResult!=0){
            return 3;
        }else{
            return userResult;
        }
    }
}
