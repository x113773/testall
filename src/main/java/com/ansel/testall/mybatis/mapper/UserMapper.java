package com.ansel.testall.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ansel.testall.mybatis.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}