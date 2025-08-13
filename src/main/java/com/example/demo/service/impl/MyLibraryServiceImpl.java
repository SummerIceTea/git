package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.persistence.entity.MyLibrary;
import com.example.demo.persistence.mapper.MyLibraryMapper;
import com.example.demo.service.MyLibraryService;

@Service
public class MyLibraryServiceImpl implements MyLibraryService {

    private final MyLibraryMapper myLibraryMapper;

    public MyLibraryServiceImpl(MyLibraryMapper myLibraryMapper) {
        this.myLibraryMapper = myLibraryMapper;
    }

    @Override
    @Transactional
    public void insert(MyLibrary myLibrary) {
        myLibraryMapper.insert(myLibrary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyLibrary> findByUserId(Integer userId) {
        List<MyLibrary> myLibraryList = myLibraryMapper.findByUserId(userId);
        return myLibraryList;
    }

    @Override
    @Transactional(readOnly = true)
    public MyLibrary findByIdandUserId(Integer id, Integer userId) {
        MyLibrary myLibrary = myLibraryMapper.findByIdandUserId(id, userId);
        return myLibrary;
    }

    @Override
    public void update(MyLibrary myLibrary) {
        myLibraryMapper.update(myLibrary);
    }

}
