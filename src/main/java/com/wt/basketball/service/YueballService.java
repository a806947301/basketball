package com.wt.basketball.service;

import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.YueballVo;

import java.util.List;

public interface YueballService {
    /**
     * 查询
     * @param text
     * @param hot
     * @return
     */
    List<YueballVo> select(String text, Integer hot);
}
