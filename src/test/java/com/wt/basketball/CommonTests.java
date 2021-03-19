package com.wt.basketball;

import com.wt.basketball.service.UserService;
import com.wt.basketball.dao.CommonMapper;
import com.wt.basketball.model.Common;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
class CommonTests {



    @Resource
    private CommonMapper commonMapper;

    @Test
    public void testSelect() {
        boolean add = commonMapper.add(new Common("这是一个评论", new Date(), "wt", 1));

        Common common = commonMapper.get(1);

        List<Common> commons = commonMapper.selectAll(1);

        common = new Common();
        common.setId(1);
        common.setContent("hello");
        boolean update = commonMapper.update(common);

        boolean delete = commonMapper.delete(1);

        System.out.println(1);
    }

    @Test
    public void add() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            boolean add = commonMapper.add(new Common("这是一个评论" + r.nextInt(), new Date(), r.nextBoolean()? "wt" : "wt1", r.nextInt(50)));
        }



    }

}
