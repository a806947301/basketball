package com.wt.basketball.model;

import java.util.Date;

/**
 * 文章
 * @author wut
 * @since 2021-03-18
 */
public class Article {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 阅读数
     */
    private Integer read;

    /**
     * 用户
     */
    private String username;

    /**
     * 是否设置为推荐，0为否，1为是
     */
    private Integer pushhot;

    /**
     * 类型，1为技术文章，2为评论区文章
     */
    private Integer type;

    public Article() {
    }

    public Article(String title, String content, Date createtime, String username, Integer type) {
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.username = username;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPushhot() {
        return pushhot;
    }

    public void setPushhot(Integer pushhot) {
        this.pushhot = pushhot;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
