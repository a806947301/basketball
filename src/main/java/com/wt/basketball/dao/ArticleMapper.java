package com.wt.basketball.dao;

import com.wt.basketball.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
public interface ArticleMapper {

    boolean add(Article article);

    /**
     * 查询
     * @param text
     * @return
     */
    List<Article> selectAll(@Param("text") String text);

    /**
     * 查一个
     * @param id
     * @return
     */
    Article get(@Param("id") String id);

    /**
     * 更新
     * @param article
     * @return
     */
    boolean update(Article article);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(@Param("id") int id);
}