package com.wt.basketball;

import com.wt.basketball.dao.ArticleMapper;
import com.wt.basketball.dao.YueballMapper;
import com.wt.basketball.model.Article;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.service.UserService;
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



    @Test
    public void testSelect() {
       // boolean addr = articleMapper.add(new Article("标题", "内容内容", new Date(), "wt", 1));

//        List<Article> articles = articleMapper.selectAll("标2");

        Yueball addY = new Yueball("xx球场", "/img/1.jpg", "球场详细信息在这里", new Date(), 10, "wt", 0);

        boolean add = yueballMapper.add(addY);

        Yueball yueball = yueballMapper.get(1);

        List<Yueball> yueballs = yueballMapper.selectAll(null, 0);

        Yueball y = new Yueball();
        y.setId(1);
        y.setPlacemsg("ballballball");
        boolean update = yueballMapper.update(y);

        boolean wt = yueballMapper.yue(1, "wt");

        boolean delete = yueballMapper.delete(1);


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
