package com.wt.basketball.service.impl;

import com.wt.basketball.dao.UserMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String username) {
        return userMapper.get(username);
    }

    @Override
    public boolean login(String username, String password) {
        User user = get(username);
        if (password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}