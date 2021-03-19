package com.wt.basketball.controller;

import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.YueballService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/yue")
@RestController
public class YueballController {

    @Autowired
    private YueballService service;

    @Resource
    private HttpServletRequest request;

    /**
     * 查询
     * @param text
     * @return
     */
    @GetMapping("/list")
    public List<YueballVo> select(String text) {
        return service.select(text, null);
    }

    /**
     * 热门
     * @return
     */
    @GetMapping("/hot")
    public List<YueballVo> selectHot() {
        return service.select(null, 1);
    }

    /**
     * 更新
     * @param yueball
     * @return
     */
    @PostMapping("/yueball")
    public boolean update(Yueball yueball){
        if (StringUtils.isEmpty(yueball.getId())) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }

        return service.update(yueball, currentUser);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/yueball")
    public boolean delete(Integer id) {
        if (null == id) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }
        return service.delete(id, currentUser);
    }

}
