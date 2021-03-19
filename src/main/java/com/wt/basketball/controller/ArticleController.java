package com.wt.basketball.controller;

import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.ArticleDetailVo;
import com.wt.basketball.model.vo.ArticleVo;
import com.wt.basketball.service.ArticleService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wut
 * @since 2021-03-18
 */
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService service;

    /**
     * 查询新闻
     * @param text
     * @return
     */
    @GetMapping("/news")
    public List<ArticleVo> newsPage(String text) {
        return service.selectNews(text, null);
    }

    /**
     * 热门新闻
     * @param
     * @return
     */
    @GetMapping("/hotnews")
    public List<ArticleVo> hotnews() {
        return service.selectNews(null, 1);
    }

    /**
     * 查询技术区
     * @param text
     * @return
     */
    @GetMapping("/tech")
    public List<ArticleVo> techPage(String text) {
        return service.selectTech(text, null);
    }

    /**
     * 热门技术区
     * @param
     * @return
     */
    @GetMapping("/hottech")
    public List<ArticleVo> hottech() {
        return service.selectTech(null, 1);
    }

    /**
     * 查询评论区
     * @param text
     * @return
     */
    @GetMapping("/common")
    public List<ArticleVo> commonPage(String text) {
        return service.selectCommon(text, null);
    }

    /**
     * 热门评论区
     * @param
     * @return
     */
    @GetMapping("/hotcommon")
    public List<ArticleVo> hotcommon() {
        return service.selectCommon(null, 1);
    }


    /**
     * 文章详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public ArticleDetailVo detail(Integer id) {
        if (null == id) {
            return null;
        }

        return service.getDetail(id);
    }

    /**
     * 获取一个
     * @param id
     * @return
     */
    @GetMapping("/article")
    public ArticleVo get(Integer id) {
        return service.get(id);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/article")
    public boolean delete(Integer id, HttpServletRequest request) {
        if (null == id) {
            return false;
        }

        // 判断登录
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser || currentUser.getIsadmin() != 1) {
            return false;
        }

        return service.delete(id);
    }

    /**
     * 添加
     * @param article
     * @param request
     * @return
     */
    @PutMapping("/article")
    public boolean add(Article article, HttpServletRequest request) {
        if (null == article || (!article.verify())) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }

        return service.add(article, currentUser);
    }

    /**
     * 更新
     * @param article
     * @param request
     * @return
     */
    @PostMapping("/article")
    public boolean update(Article article, HttpServletRequest request) {
        if (null == article || StringUtils.isEmpty(article.getId())) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }

        return service.update(article, currentUser);
    }
}
