package com.wt.basketball.service.impl;


import com.wt.basketball.dao.ArticleMapper;
import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.ArticleVo;
import com.wt.basketball.service.ArticleService;
import com.wt.basketball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public List<ArticleVo> selectNews(String text, Integer pushhot) {
        return selectVo(text, 3, pushhot);
    }

    @Override
    public List<ArticleVo> selectTech(String text, Integer pushhot) {
        return selectVo(text, 1, pushhot);
    }

    @Override
    public List<ArticleVo> selectCommon(String text, Integer pushhot) {
        return selectVo(text, 2, pushhot);
    }

    private List<ArticleVo> selectVo(String text, Integer type, Integer pushhot) {
        List<Article> articles = mapper.selectAll(text, type, pushhot);
        ArrayList<ArticleVo> result = new ArrayList<>(articles.size());
        for (Article article : articles) {
            User user = userService.get(article.getUsername());
            result.add(new ArticleVo(article, user));
        }

        return result;
    }
}
