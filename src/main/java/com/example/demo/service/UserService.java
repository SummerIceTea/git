package com.example.demo.service;

import com.example.demo.persistence.entity.User;

//作るときはこっちが先？
public interface UserService {

    User login(String loginId, String password);
}
