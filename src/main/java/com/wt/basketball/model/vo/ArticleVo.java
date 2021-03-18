package com.wt.basketball.model.vo;

import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import org.springframework.beans.BeanUtils;

/**
 * @author wut
 * @since 2021-03-18
 */
public class ArticleVo extends Article {

    User user;

    public ArticleVo(Article article, User user) {
        BeanUtils.copyProperties(article, this);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
