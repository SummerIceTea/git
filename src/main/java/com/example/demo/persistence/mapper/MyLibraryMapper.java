package com.example.demo.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.persistence.entity.MyLibrary;

@Mapper
public interface MyLibraryMapper {

    @Insert("INSERT INTO mylibrary(user_id, title, author, shop_name, buy_date, comments)" +
            "VALUES(#{userId}, #{title}, #{author}, #{shopName}, #{buyDate}, #{comments})")
    void insert(MyLibrary myLibrary);

    @Select("SELECT id, user_id, title, author, shop_name, buy_date, comments FROM mylibrary WHERE user_id = #{userId}")
    List<MyLibrary> findByUserId(Integer userId);

    @Select("SELECT id, user_id, title, author, shop_name, buy_date, comments FROM mylibrary WHERE id = #{id} AND  user_id = #{userId}")
    MyLibrary findByIdandUserId(Integer id, Integer userId);

    @Update("UPDATE mylibrary SET title = #{title}, author = #{author}, shop_name = #{shopName}, buy_date = #{buyDate}, comments = #{comments} WHERE id = #{id} AND user_id = #{userId}")
    void update(MyLibrary myLibrary);

}
