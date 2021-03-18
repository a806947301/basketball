package com.wt.basketball;

import com.wt.basketball.service.UserService;
import com.wt.basketball.dao.ArticleMapper;
import com.wt.basketball.model.Article;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class ArticleTests {



    @Resource
    private ArticleMapper articleMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
       // boolean addr = articleMapper.add(new Article("标题", "内容内容", new Date(), "wt", 1));

//        List<Article> articles = articleMapper.selectAll("标2");

        Article a = new Article("这是一个title", "cont", new Date(), "wt1", 2);
        a.setId(1);
        boolean update = articleMapper.update(a);
        System.out.println(1);
    }

    @Test
    public void addMore() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            Article news = new Article("这是一个新闻" + i, "一大堆字", new Date(), r.nextBoolean() ? "wt1" : "wt", 3);
            articleMapper.add(news);
        }
    }

}
