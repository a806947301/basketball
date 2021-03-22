package com.wt.basketball.dao;

import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.FriendYueballDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Repository
public interface YueballMapper {
    boolean add(Yueball yueball);

    /**
     * 查询
     * @param text
     * @return
     */
    List<Yueball> selectAll(@Param("text") String text, @Param("hot") Integer hot);

    /**
     * 查询用户约球的球场
     * @param username
     * @return
     */
    List<FriendYueballDto> selectByUser(@Param("username") String username);

    /**
     * 查一个
     * @param id
     * @return
     */
    Yueball get(@Param("id") int id);

    /**
     * 更新
     * @param article
     * @return
     */
    boolean update(Yueball article);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(@Param("id") int id);

    /**
     * 约球
     * @param ballid 球场id
     * @param username  用户
     * @return
     */
    boolean yue(@Param("ballid") int ballid, @Param("username") String username);

    /**
     * 取消约球
     * @param ballid 球场id(可为空)
     * @param username  用户（可为空）
     * @return
     */
    boolean unYue(@Param("ballid") int ballid, @Param("username") String username);


    /**
     * 添加约球次数
     * @param ballid
     * @param addnumber
     * @return
     */
    boolean addYue(@Param("ballid") int ballid,@Param("addnumber") int addnumber);
}
