package com.wt.basketball.service.impl;

import com.wt.basketball.common.MyUtil;
import com.wt.basketball.dao.CommonMapper;
import com.wt.basketball.model.Common;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.CommonVo;
import com.wt.basketball.service.CommonService;
import com.wt.basketball.service.UserService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper mapper;

    @Autowired
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<CommonVo> articleCommon(Integer articleId) {
        // 获取当前用户名
        User currentUser = SessionUtil.getCurrentUser(request);
        String myUsername = "";
        if (null != currentUser) {
            myUsername = currentUser.getUsername();
        }

        List<Common> commons = mapper.selectAll(articleId);
        List<CommonVo> result = new ArrayList<>(commons.size());
        for (Common c : commons) {
            // 设置昵称
            User user = userService.get(c.getUsername());
            String nickname = null;
            if (null != user) {
                nickname = user.getNickname();
            }

            CommonVo vo = new CommonVo(c, nickname);

            // 设置是否我的评论
            if (myUsername.equals(c.getUsername())) {
                vo.setMyCommon(true);
            } else {
                vo.setMyCommon(false);
            }
            result.add(vo);
        }
        return result;
    }

    @Override
    public boolean writeCommon(Common common) {
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }

        common.setCreatetime(new Date());
        common.setGood(0);
        common.setUsername(currentUser.getUsername());
        return mapper.add(common);
    }

    @Override
    public boolean deleteCommon(int id) {
        User me = SessionUtil.getCurrentUser(request);
        if (null == me) {
            return false;
        }

        //非管理员且非自己评论不允许删除
        Common common = mapper.get(id);
        if(me.getIsadmin() == 0 && (!me.getUsername().equals(common.getUsername()))) {
            return false;
        }

        return mapper.delete(id);
    }

    @Override
    public boolean addGood(int id, User currentUser) {
        MyUtil.addGoodCommon(currentUser.getUsername(), id);
        return mapper.addGood(id);
    }

    @Override
    public boolean addLike(int id, User currentUser) {
        MyUtil.addLikeCommon(currentUser.getUsername(), id);
        return mapper.addLike(id);
    }

    @Override
    public boolean addUnlike(int id, User currentUser) {
        MyUtil.addUnlikeCommon(currentUser.getUsername(), id);
        return mapper.addUnlike(id);
    }
}
