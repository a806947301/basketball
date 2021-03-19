package com.wt.basketball.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author wut
 * @since 2021-03-18
 */
public class Common {
    /**
     * id
     */
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createtime;

    /**
     * 点赞数
     */
    private Integer good;

    /**
     * 用户
     */
    private String username;


    /**
     * 评论谁
     */
    private Integer cwho;

    public Common() {
    }

    public Common(String content, Date createtime, String username, Integer cwho) {
        this.content = content;
        this.createtime = createtime;
        this.username = username;
        this.cwho = cwho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCwho() {
        return cwho;
    }

    public void setCwho(Integer cwho) {
        this.cwho = cwho;
    }
}
