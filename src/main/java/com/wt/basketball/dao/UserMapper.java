package com.wt.basketball.dao;

import com.wt.basketball.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {

    List<User> selectAll();

    /**
     * 根据约球球场查询与该球场有关的用户
     * @param ballid
     * @return
     */
    List<User> selectByBall(@Param("ballid") Integer ballid);

    /**
     * 根据username获取
     * @param username
     * @return
     */
    User get(@Param("username") String username);

    /**
     * 更新
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 删除
     * @param username
     * @return
     */
    boolean delete(@Param("username") String username);
}
