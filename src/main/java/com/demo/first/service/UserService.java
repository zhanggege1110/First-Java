package com.demo.first.service;

import com.demo.first.model.User;

import java.util.List;

public interface UserService {
    int insert(User record);

    User login(User record);

    List<User> selectUserByName(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(User record);

    int deleteByPrimaryKey(Integer id);


}
