package com.wt.basketball.service.impl;

import com.wt.basketball.dao.YueballMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.UserService;
import com.wt.basketball.service.YueballService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class YueballServiceImpl implements YueballService {

    @Autowired
    private YueballMapper mapper;

    @Autowired
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<YueballVo> select(String text, Integer hot) {
        User currentUser = SessionUtil.getCurrentUser(request);

        List<Yueball> yueballs = mapper.selectAll(text, hot);
        List<YueballVo> reuslt = new ArrayList<>(yueballs.size());
        for (Yueball yueball : yueballs) {
            User user = userService.get(yueball.getUsername());
            YueballVo vo = new YueballVo(yueball, user);
            if (null != currentUser && currentUser.getUsername().equals(yueball.getUsername())) {
                vo.setMyYue(true);
            } else {
                vo.setMyYue(false);
            }

            reuslt.add(vo);
        }

        return reuslt;
    }

    @Override
    public boolean update(Yueball yueball, User currentUser) {
        // 用户校验
        if (null == currentUser) {
            // 热门只有管理员可以校验
            if (null != yueball.getHot() && currentUser.getIsadmin() == 0) {
                return false;
            }

            // 其他的本人和管理员皆可以校验
            Yueball old = mapper.get(yueball.getId());
            if (currentUser.getIsadmin() == 0 && (!old.getUsername().equals(currentUser.getUsername()))) {
                return false;
            }
        }

        return mapper.update(yueball);
    }

    @Override
    public boolean delete(Integer id, User currentUser) {
        if (null != currentUser) {
            // 非管理员且非本人不能删
            Yueball old = mapper.get(id);
            if (currentUser.getIsadmin() == 0 && (!currentUser.getUsername().equals(old.getUsername()))) {
                return false;
            }
        }
        return mapper.delete(id);
    }
}
