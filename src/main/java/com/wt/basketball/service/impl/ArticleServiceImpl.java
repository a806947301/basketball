package com.wt.basketball.service.impl;


import com.wt.basketball.common.AppException;
import com.wt.basketball.common.MyUtil;
import com.wt.basketball.dao.ArticleMapper;
import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.ArticleDetailVo;
import com.wt.basketball.model.vo.ArticleVo;
import com.wt.basketball.service.ArticleService;
import com.wt.basketball.service.CommonService;
import com.wt.basketball.service.UserService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Resource
    private HttpServletRequest request;

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

    @Override
    public ArticleVo get(Integer id) {
        Article article = mapper.get(id);
        if (null == article) {
            return null;
        }

        User user = userService.get(article.getUsername());

        return new ArticleVo(article, user);
    }

    @Override
    public ArticleDetailVo getDetail(Integer id) {
        // 增加阅读数
        mapper.addRead(id);

        User me = SessionUtil.getCurrentUser(request);

        Article article = mapper.get(id);
        if (null == article) {
            return null;
        }

        User user = userService.get(article.getUsername());

        ArticleDetailVo vo = new ArticleDetailVo(article, user);

        // 是否我的文章
        if (null != me && vo.getUsername().equals(me.getUsername())) {
            vo.setMyArticle(true);
        } else {
            vo.setMyArticle(false);
        }

        vo.setCommons(commonService.articleCommon(id));
        return vo;
    }

    @Override
    public boolean delete(Integer id) {
        return mapper.delete(id);
    }

    @Override
    public boolean deleteByUc(String username) {
        return mapper.deleteByUsername(username);
    }

    @Override
    public boolean update(Article article, User currentUser) {
        Article old = mapper.get(article.getId());
        if (currentUser.getIsadmin() == 1 || old.getUsername().equals(currentUser.getUsername())) {
            return mapper.update(article);
        }
        return false;
    }

    @Override
    public Integer add(Article article, User currentUser) {
        article.setCreatetime(new Date());
        article.setPushhot(0);
        article.setRead(0);
        article.setUsername(currentUser.getUsername());

        if (mapper.add(article)) {
            return article.getId();
        }

        return null;
    }

    @Override
    public boolean addGood(Integer id, User currentUser) {
        MyUtil.addGood(currentUser.getUsername(), id);
        return mapper.addGood(id);
    }

    /**
     * 查询vo
     * @param text
     * @param type
     * @param pushhot
     * @return
     */
    private List<ArticleVo> selectVo(String text, Integer type, Integer pushhot) {
        User currentUser = SessionUtil.getCurrentUser(request);

        List<Article> articles = mapper.selectAll(text, type, pushhot);
        ArrayList<ArticleVo> result = new ArrayList<>(articles.size());
        for (Article article : articles) {
            User user = userService.get(article.getUsername());
            ArticleVo vo = new ArticleVo(article, user);

            // 判断是否自己的文章
            if (null == currentUser || (!currentUser.getUsername().equals(article.getUsername()))) {
                vo.setMyArticle(false);
            } else {
                vo.setMyArticle(true);
            }

            result.add(vo);
        }

        return result;
    }

    @Override
    public boolean addLike(int id, User currentUser) {
        MyUtil.addLikeArticle(currentUser.getUsername(), id);
        return mapper.addLike(id);
    }

    @Override
    public boolean addUnlike(int id, User currentUser) {
        MyUtil.addUnlikeArticle(currentUser.getUsername(), id);
        return mapper.addUnlike(id);
    }
}
