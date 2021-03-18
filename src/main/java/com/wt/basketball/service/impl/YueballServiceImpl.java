package com.wt.basketball.service.impl;

import com.wt.basketball.dao.YueballMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.UserService;
import com.wt.basketball.service.YueballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YueballServiceImpl implements YueballService {

    @Autowired
    private YueballMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public List<YueballVo> select(String text, Integer hot) {
        List<Yueball> yueballs = mapper.selectAll(text, hot);
        List<YueballVo> reuslt = new ArrayList<>(yueballs.size());
        for (Yueball yueball : yueballs) {
            User user = userService.get(yueball.getUsername());
            reuslt.add(new YueballVo(yueball, user));
        }

        return reuslt;
    }
}
