package com.wt.basketball.model.vo;

import com.wt.basketball.model.Article;
import com.wt.basketball.model.Common;
import com.wt.basketball.model.User;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-19
 */
public class ArticleDetailVo extends ArticleVo {

    List<CommonVo> commons;

    public ArticleDetailVo(Article article, User user) {
        super(article, user);
    }

    public List<CommonVo> getCommons() {
        return commons;
    }

    public void setCommons(List<CommonVo> commons) {
        this.commons = commons;
    }
}
