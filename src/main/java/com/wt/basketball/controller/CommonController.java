package com.wt.basketball.controller;

import com.wt.basketball.common.BizResult;
import com.wt.basketball.model.Common;
import com.wt.basketball.model.User;
import com.wt.basketball.service.CommonService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    private CommonService service;

    @Resource
    private HttpServletRequest request;

    /**
     * 写评论
     * @param common
     * @return
     */
    @PutMapping("/common")
    public boolean write(Common common) {
        if (StringUtils.isEmpty(common.getContent())) {
            return false;
        }
        if (null == common.getCwho()) {
            return false;
        }

        return service.writeCommon(common);
    }

    /**
     * 删评论
     * @param id
     * @return
     */
    @DeleteMapping("/common")
    public boolean delete(Integer id) {
        if (null == id) {
            return false;
        }

        return service.deleteCommon(id);
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @PostMapping("/good")
    public BizResult addGood(Integer id) {
        if (null == id) {
            return BizResult.fall("id不能为空");
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }

        if (service.addGood(id, currentUser)) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }

    /**
     * 喜欢
     * @param id
     * @return
     */
    @PostMapping("/like")
    public BizResult addLike(Integer id) {
        if (null == id) {
            return BizResult.fall("id不能为空");
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }

        if (service.addLike(id, currentUser)) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }


    /**
     * 不喜欢
     * @param id
     * @return
     */
    @PostMapping("/unlike")
    public BizResult addUnlike(Integer id) {
        if (null == id) {
            return BizResult.fall("id不能为空");
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }

        if (service.addUnlike(id, currentUser)) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }
}
