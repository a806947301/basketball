package com.wt.basketball.model.vo;

import com.wt.basketball.model.Common;
import org.springframework.beans.BeanUtils;

/**
 * @author wut
 * @since 2021-03-19
 */
public class CommonVo extends Common {
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 是否我的评论
     */
    private Boolean myCommon;

    public CommonVo(Common common, String nickname) {
        BeanUtils.copyProperties(common, this);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getMyCommon() {
        return myCommon;
    }

    public void setMyCommon(Boolean myCommon) {
        this.myCommon = myCommon;
    }
}
