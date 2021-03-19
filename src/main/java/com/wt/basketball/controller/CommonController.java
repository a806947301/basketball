package com.wt.basketball.controller;

import com.wt.basketball.model.Common;
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

}
