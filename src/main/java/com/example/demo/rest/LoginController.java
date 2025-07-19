package com.example.demo.rest;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.persistence.entity.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.UserService;
import com.example.demo.session.LoginUser;


@Controller
@RequestMapping("/api/login")
public class LoginController {
    private final UserService userService;
    private final LoginUser loginUser;

    public LoginController(LoginUser loginUser, UserService userService) {
        this.loginUser = loginUser;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> login(@RequestBody LoginRequest request){
        User user = userService.login(request.getLoginId(), request.getPassword());
        System.out.println("User:" + user);

        loginUser.login(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public LoginResponse getLoginUser(){
        User user = loginUser.getUser();
        return new LoginResponse(user.getId(), user.getLoginId(), user.getName());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(){
        loginUser.logout();
    }
}
