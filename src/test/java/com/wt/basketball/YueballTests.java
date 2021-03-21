package com.wt.basketball;

import com.wt.basketball.dao.ArticleMapper;
import com.wt.basketball.dao.YueballMapper;
import com.wt.basketball.model.Article;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.FriendYueballDto;
import com.wt.basketball.service.UserService;
import com.wt.basketball.service.YueballService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class YueballTests {



    @Resource
    private YueballMapper yueballMapper;

    private YueballService yueballService;


    @Test
    public void testSelect() {
        List<FriendYueballDto> wt2 = yueballMapper.selectByUser("wt2");

        System.out.println(1);
    }


    @Test
    public void batchAdd() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            Yueball addY = new Yueball(r.nextInt(100) + "xx球场" , "/img/1.jpg", "球场详细信息在这里", new Date(), 0, r.nextBoolean() ? "wt" : "wt1", r.nextInt(2));
            yueballMapper.add(addY);
        }
    }

}
