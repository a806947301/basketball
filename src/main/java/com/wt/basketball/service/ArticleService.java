package com.wt.basketball.service;

import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.ArticleDetailVo;
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

    /**
     * 获取一个
     * @param id
     * @return
     */
    ArticleVo get(Integer id);

    /**
     * 获取详情（包括评论）
     * @param id
     * @return
     */
    ArticleDetailVo getDetail(Integer id);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 删除用户的所有文章
     * @param username
     * @return
     */
    boolean deleteByUc(String username);

    /**
     * 更新文章
     * @param article
     * @param currentUser
     * @return
     */
    boolean update(Article article, User currentUser);

    /**
     * 添加
     * @param article
     * @param currentUser
     * @return
     */
    Integer add(Article article, User currentUser);

    /**
     * 增加点赞数
     * @param id
     * @return
     */
    boolean addGood(Integer id, User currentUser);

}
