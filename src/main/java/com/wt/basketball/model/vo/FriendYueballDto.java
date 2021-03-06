package com.wt.basketball.model.vo;

import java.util.Date;

public class FriendYueballDto {
    /**
     * id
     */
    private Integer id;

    /**
     * 球场名
     */
    private String name;

    /**
     * 约球信息创建人
     */
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
