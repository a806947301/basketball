package com.wt.basketball.service;

import com.wt.basketball.model.User;

import java.util.List;

public interface UserService {
    /**
     * 获取单个
     * @param username
     * @return
     */
    public User get(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return  如果登录成功，则返回user对象
     */
    public User  login(String username, String password);

    /**
     * 更新
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 查询
     * @param ballid
     * @return
     */
    public List<User> search(Integer ballid);
}
