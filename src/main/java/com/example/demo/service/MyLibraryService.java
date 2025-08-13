package com.example.demo.service;

import java.util.List;

import com.example.demo.persistence.entity.MyLibrary;

public interface MyLibraryService {
    void insert(MyLibrary myLibrary);

    List<MyLibrary> findByUserId(Integer userId);

    MyLibrary findByIdandUserId(Integer id, Integer userId);

    void update(MyLibrary myLibrary);

}
