package com.wt.basketball.model.vo;

import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import org.springframework.beans.BeanUtils;

public class YueballVo extends Yueball {
    User user;

    public YueballVo(Yueball yueball, User user) {
        BeanUtils.copyProperties(yueball, this);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
