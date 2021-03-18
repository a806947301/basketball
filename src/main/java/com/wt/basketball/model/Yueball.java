package com.wt.basketball.model;

import java.util.Date;

/**
 * 约球信息
 * @author wut
 * @since 2021-03-18
 */
public class Yueball {
    /**
     * id
     */
    private Integer id;

    /**
     * 球场名
     */
    private String name;

    /**
     * 图片URL
     */
    private String imgsrc;

    /**
     * 球场信息
     */
    private String placemsg;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 约球人数
     */
    private Integer yue;

    /**
     * 用户
     */
    private String username;

    private Integer hot;

    public Yueball() {
    }

    public Yueball(String name, String imgsrc, String placemsg, Date createtime, Integer yue, String username, Integer hot) {
        this.name = name;
        this.imgsrc = imgsrc;
        this.placemsg = placemsg;
        this.createtime = createtime;
        this.yue = yue;
        this.username = username;
        this.hot = hot;
    }

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

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPlacemsg() {
        return placemsg;
    }

    public void setPlacemsg(String placemsg) {
        this.placemsg = placemsg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getYue() {
        return yue;
    }

    public void setYue(Integer yue) {
        this.yue = yue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
