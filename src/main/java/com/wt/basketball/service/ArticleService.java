package com.wt.basketball.service;

import com.wt.basketball.model.vo.ArticleVo;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
public interface ArticleService {
    /**
     * 查询新闻
     * @param text
     * @param pushhot
     * @return
     */
    List<ArticleVo> selectNews(String text, Integer pushhot);

    /**
     * 查询技术区
     * @param text
     * @param pushhot
     * @return
     */
    List<ArticleVo> selectTech(String text, Integer pushhot);

    /**
     * 查询评论区
     * @param text
     * @param pushhot
     * @return
     */
    List<ArticleVo> selectCommon(String text, Integer pushhot);

}
