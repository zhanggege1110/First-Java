package com.demo.first.service.impl;

import com.demo.first.dao.UserMapper;
import com.demo.first.model.User;
import com.demo.first.model.UserExample;
import com.demo.first.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    public  int insert(User record){return mapper.insert(record);}

    public List<User> selectUserByName(User record){

        UserExample userExample = new UserExample();
        userExample.or().andNameEqualTo(record.getName());
        List<User> users = mapper.selectByExample(userExample);
        return users;
    }

    public User login(User record){

        UserExample userExample = new UserExample();
        userExample.or().andNameEqualTo(record.getName());
        List<User> users = mapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        return users.get(0);
    }
    public User selectByPrimaryKey(Integer id){
        return mapper.selectByPrimaryKey(id);}

    public int updateByPrimaryKey(User record){
        return mapper.updateByPrimaryKey(record);}

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }


}

