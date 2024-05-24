package com.example.dao;


import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
@Mapper
public interface UserEntityMapper {
    //登录页面
    List<User> login(User user);

    //注册页面
    int eroll(User user);


}
