package com.wt.basketball.model.vo;

import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;

import java.util.List;

public class YueballDetailVo extends YueballVo {

    List<User> yueFriends;

    /**
     * 是否已约
     */
    Boolean meYue;

    public YueballDetailVo(Yueball yueball, User user) {
        super(yueball, user);
    }

    public List<User> getYueFriends() {
        return yueFriends;
    }

    public void setYueFriends(List<User> yueFriends) {
        this.yueFriends = yueFriends;
    }

    public Boolean getMeYue() {
        return meYue;
    }

    public void setMeYue(Boolean meYue) {
        this.meYue = meYue;
    }
}
