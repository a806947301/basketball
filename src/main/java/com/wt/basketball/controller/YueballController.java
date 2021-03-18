package com.wt.basketball.controller;

import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.YueballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/yue")
@RestController
public class YueballController {

    @Autowired
    private YueballService service;

    /**
     * 查询
     * @param text
     * @return
     */
    @GetMapping("/list")
    public List<YueballVo> select(String text) {
        return service.select(text, 0);
    }

    /**
     * 热门
     * @return
     */
    @GetMapping("/hot")
    public List<YueballVo> selectHot() {
        return service.select(null, 1);
    }


}
