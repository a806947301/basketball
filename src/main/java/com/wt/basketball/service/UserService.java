package com.wt.basketball.service;

import com.wt.basketball.model.User;

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
     * @return
     */
    public boolean login(String username, String password);
}
