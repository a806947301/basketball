package com.wt.basketball;

import com.wt.basketball.service.UserService;
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
        List<User> wt = userService.myFriend("wt");

        System.out.println(1);
    }

}
