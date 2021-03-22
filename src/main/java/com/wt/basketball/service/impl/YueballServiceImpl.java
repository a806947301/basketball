package com.wt.basketball.service.impl;

import com.wt.basketball.common.AppException;
import com.wt.basketball.dao.YueballMapper;
import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.FriendYueballDto;
import com.wt.basketball.model.vo.YueballDetailVo;
import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.UserService;
import com.wt.basketball.service.YueballService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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
    public List<FriendYueballDto> selectByUser(String username) {
        return mapper.selectByUser(username);
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

        // 删除所有用户的约球信息
        mapper.unYue(id, null);

        return mapper.delete(id);
    }

    @Override
    public boolean add(Yueball yueball, User currentUser) {
        // 自己也添加进约球人列表


        yueball.setCreatetime(new Date());
        yueball.setHot(0);
        yueball.setUsername(currentUser.getUsername());
        yueball.setYue(1);
        boolean addResult = mapper.add(yueball);

        if (addResult) {
            mapper.yue(yueball.getId(), currentUser.getUsername());
        }
        return addResult;
    }

    @Override
    public YueballDetailVo getYueballDetail(Integer id) {

        Yueball yueball = mapper.get(id);
        if (null == yueball) {
            return null;
        }
        User user = userService.get(yueball.getUsername());

        YueballDetailVo vo = new YueballDetailVo(yueball, user);


        // 是否本人
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null != currentUser && currentUser.getUsername().equals(yueball.getUsername())) {
            vo.setMyYue(true);
        } else {
            vo.setMyYue(false);
        }

        // 球友
        List<User> ballFriends = userService.search(id);
        vo.setYueFriends(ballFriends);

        // 设置是否已约
        vo.setMeYue(false);
        if (null != currentUser) {
            for (User ballFriend : ballFriends) {
                if (currentUser.getUsername().equals(ballFriend.getUsername())) {
                    vo.setMeYue(true);
                }
            }
        }
        return vo;
    }

    @Override
    public boolean yue(Integer ballid) {
        User currentUser = SessionUtil.getCurrentUser(request);

        if (null ==currentUser) {
            throw new AppException("请登录");
        }

        mapper.addYue(ballid, 1);

        return mapper.yue(ballid, currentUser.getUsername());
    }

    @Override
    public boolean unYue(Integer ballid) {
        User currentUser = SessionUtil.getCurrentUser(request);

        if (null ==currentUser) {
            throw new AppException("请登录");
        }

        mapper.addYue(ballid, -1);

        return  mapper.unYue(ballid, currentUser.getUsername());
    }
}
