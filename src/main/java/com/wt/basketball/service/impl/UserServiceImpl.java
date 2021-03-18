package com.wt.basketball.service.impl;

import com.wt.basketball.dao.UserMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(String username) {
        return userMapper.get(username);
    }

    @Override
    public User login(String username, String password) {
        User user = get(username);
        if (null != user && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<User> search(Integer ballid) {
        return userMapper.selectByBall(ballid);
    }
}
