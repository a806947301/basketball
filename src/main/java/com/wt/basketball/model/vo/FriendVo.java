package com.wt.basketball.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wt.basketball.model.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendVo extends User {

    /**
     * 球场
     */
    List<FriendYueballDto> ball;




    public FriendVo(User user, FriendYueballDto dto) {
        BeanUtils.copyProperties(user, this);
        this.ball = new ArrayList<>();
        ball.add(dto);
    }

    /**
     * 添加球场
     * @param dto
     */
    public void addBall(FriendYueballDto dto) {
        ball.add(dto);
    }

    public List<FriendYueballDto> getBall() {
        return ball;
    }

    public void setBall(List<FriendYueballDto> ball) {
        this.ball = ball;
    }
}
