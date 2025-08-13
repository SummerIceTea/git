package com.example.demo.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.entity.MyLibrary;
import com.example.demo.persistence.entity.User;
import com.example.demo.service.MyLibraryService;
import com.example.demo.web.request.MyLibraryRequest;
import com.example.demo.web.response.MyLibraryResponse;
import com.example.demo.web.session.LoginUser;

@RestController
@RequestMapping("/api/mylibrary")
public class MyLibraryController {

    private final MyLibraryService myLibraryService;
    private final LoginUser loginUser;

    public MyLibraryController(MyLibraryService myLibraryService, LoginUser loginUser) {
        this.myLibraryService = myLibraryService;
        this.loginUser = loginUser;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody MyLibraryRequest request) {
        // セッションからログイン中のユーザー取得
        User user = loginUser.getUser();

        MyLibrary myLibrary = new MyLibrary();
        myLibrary.setUserId(user.getId()); // userIdをサーバーで設定
        myLibrary.setTitle(request.getTitle());
        myLibrary.setAuthor(request.getAuthor());

        myLibraryService.insert(myLibrary);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public List<MyLibraryResponse> findByUserId() {
        Integer userId = loginUser.getUser().getId();
        List<MyLibrary> myLibraryList = myLibraryService.findByUserId(userId);

        List<MyLibraryResponse> myLibraryResponseList = new ArrayList<>();
        for (MyLibrary myLibrary : myLibraryList) {
            MyLibraryResponse myLibraryResponse = new MyLibraryResponse(
                    myLibrary.getId(),
                    myLibrary.getTitle(),
                    myLibrary.getAuthor(),
                    myLibrary.getShopName(),
                    myLibrary.getBuyDate(),
                    myLibrary.getComments());
            myLibraryResponseList.add(myLibraryResponse);
        }

        return myLibraryResponseList;
    }

    @GetMapping("/{id}")
    public MyLibraryResponse findByIdandUserId(@PathVariable Integer id) {
        Integer userId = loginUser.getUser().getId();
        MyLibrary myLibrary = myLibraryService.findByIdandUserId(id, userId);
        MyLibraryResponse myLibraryResponse = new MyLibraryResponse(
                myLibrary.getId(),
                myLibrary.getTitle(),
                myLibrary.getAuthor(),
                myLibrary.getShopName(),
                myLibrary.getBuyDate(),
                myLibrary.getComments());
        return myLibraryResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody MyLibraryRequest request) {
        Integer userId = loginUser.getUser().getId();
        MyLibrary myLibrary = new MyLibrary();
        myLibrary.setId(id);
        myLibrary.setUserId(userId);
        myLibrary.setTitle(request.getTitle());
        myLibrary.setAuthor(request.getAuthor());
        myLibrary.setComments(request.getComments());
        myLibrary.setBuyDate(request.getBuyDate());
        myLibrary.setShopName(request.getShopName());

        myLibraryService.update(myLibrary);

    }
}
