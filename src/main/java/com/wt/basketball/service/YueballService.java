package com.wt.basketball.service;

import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.FriendYueballDto;
import com.wt.basketball.model.vo.YueballDetailVo;
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
     * 查询
     * @param username
     * @return
     */
    List<FriendYueballDto> selectByUser(String username);



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

    /**
     * 删除用户的所有约球信息
     * @param username
     * @return
     */
    boolean deleteByUc(String username);

    /**
     * 添加
     * @param yueball
     * @param currentUser
     * @return
     */
    boolean add(Yueball yueball, User currentUser);

    /**
     * 获取详情
     * @param id
     * @return
     */
    YueballDetailVo getYueballDetail(Integer id) ;

    /**
     * 约球
     * @param ballid 球场id
     * @return
     */
    boolean yue(Integer ballid);

    /**
     * 取消约球
     * @param ballid 球场id
     * @return
     */
    boolean unYue(Integer ballid);
}
