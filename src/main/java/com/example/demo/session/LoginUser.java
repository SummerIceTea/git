package com.example.demo.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.exception.AuthenticationException;
import com.example.demo.persistence.entity.User;

@Component
@SessionScope
public class LoginUser {
    private User user;

    public void login(User user){
        this.user = user;
    }

    public void logout(){
        this.user = null;
    }

    public void verifyLogined(){
        if(user == null){
            throw new AuthenticationException("ログインしていません");
        }
    }

    public User getUser(){
        if(user == null){
            throw new AuthenticationException("ログインしていません");
        }
        return user;
    }

}
