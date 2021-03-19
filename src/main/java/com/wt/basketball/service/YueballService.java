package com.wt.basketball.service;

import com.wt.basketball.model.User;
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

    /**
     * 更新
     * @param yueball
     * @param currentUser   如果为空则不进行用户校验
     * @return
     */
    boolean update(Yueball yueball, User currentUser);

    /**
     * 删除
     * @param id
     * @param currentUser 如果为空则不进行用户校验
     * @return
     */
    boolean delete(Integer id, User currentUser);
}
