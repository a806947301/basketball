package com.wt.basketball.dao;

import com.wt.basketball.model.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Repository
public interface ArticleMapper {

    boolean add(Article article);

    /**
     * 查询
     * @param text
     * @return
     */
    List<Article> selectAll(@Param("text") String text, @Param("type") Integer type,
                            @Param("pushhot")Integer pushhot);

    /**
     * 查一个
     * @param id
     * @return
     */
    Article get(@Param("id") Integer id);

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

    @Delete("delete from article where username = #{username}")
    boolean deleteByUsername(@Param("username")String username);

    /**
     * 增加阅读数
     * @param id
     * @return
     */
    boolean addRead(@Param("id") int id);
}
