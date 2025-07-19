package com.example.demo.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.persistence.entity.User;

@Mapper
public interface UserMapper {

    @Select("""
            select *
            from user
            where login_id = #{loginId}
            """)
    User findByLoginId(String loginId);
}
