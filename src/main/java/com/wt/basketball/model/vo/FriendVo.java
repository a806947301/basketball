package com.wt.basketball.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wt.basketball.model.User;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class FriendVo extends User {

    /**
     * 球场名
     */
    String ballName;

    /**
     * 球场id
     */
    Integer ballId;

    /**
     * 约球时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    Date yueTime;

    public FriendVo(User user, String ballName, Integer ballId, Date yueTime) {
        BeanUtils.copyProperties(user, this);
        this.ballName = ballName;
        this.ballId = ballId;
        this.yueTime = yueTime;
    }

    public String getBallName() {
        return ballName;
    }

    public void setBallName(String ballName) {
        this.ballName = ballName;
    }

    public Integer getBallId() {
        return ballId;
    }

    public void setBallId(Integer ballId) {
        this.ballId = ballId;
    }

    public Date getYueTime() {
        return yueTime;
    }

    public void setYueTime(Date yueTime) {
        this.yueTime = yueTime;
    }
}
