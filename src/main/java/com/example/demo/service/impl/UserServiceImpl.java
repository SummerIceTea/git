package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AuthenticationException;
import com.example.demo.persistence.entity.User;
import com.example.demo.persistence.mapper.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //↑このServiceはUserMapperが必要です。外から渡してね。そしてそれをこのクラスの中で使うために保存しておきます。という意味

    @Transactional(readOnly=true)
    @Override
    public User login(String loginId, String password){
        User user = userMapper.findByLoginId(loginId);

        if(user == null || !user.getPassword().equals(password)){
            throw new AuthenticationException("認証に失敗しました");
        }

        return user;

    }
}
