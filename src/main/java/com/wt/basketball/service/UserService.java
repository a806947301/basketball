package com.wt.basketball.service;

import com.wt.basketball.model.User;

import java.util.List;

public interface UserService {
    /**
     * 获取单个
     * @param username
     * @return
     */
     User get(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return  如果登录成功，则返回user对象
     */
     User login(String username, String password);


    /**
     * 注册
     * @param user
     * @return
     */
     boolean register(User user);

    /**
     * 添加
     * @param user
     * @return
     */
     boolean add(User user);

    /**
     * 更新
     * @param user
     * @return
     */
     boolean update(User user);

    /**
     * 删除
     * @param username
     * @return
     */
    boolean delete(String username);

    /**
     * 查询
     * @param ballid
     * @return
     */
     List<User> search(Integer ballid);

    /**
     * 查询
     * @return
     */
     List<User> search();

    /**
     * 我的球友
     * @param username 当前登录用户
     * @return
     */
     List<User> myFriend(String username);
}
