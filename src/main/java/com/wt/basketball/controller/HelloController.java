package com.wt.basketball.controller;

import com.wt.basketball.dao.UserMapper;
import com.wt.basketball.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-17
 */
@RestController
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/test")
    public List test() {
        List<User> users = userMapper.selectAll();

        return users;
    }
}
