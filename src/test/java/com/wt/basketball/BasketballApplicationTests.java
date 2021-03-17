package com.wt.basketball;

import com.wt.basketball.controller.service.UserService;
import com.wt.basketball.dao.UserMapper;
import com.wt.basketball.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class BasketballApplicationTests {



    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectAll();

        User wt = userMapper.get("wt");


        //boolean result = userMapper.update(new User("wt", "123", "梧桐", "13800138000", "wut@qq.com", 0));

        boolean result = userService.login("wt", "123");
        System.out.println(users);
    }

}
