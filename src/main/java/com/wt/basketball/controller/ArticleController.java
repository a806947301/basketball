package com.wt.basketball.controller;

import com.wt.basketball.model.vo.ArticleVo;
import com.wt.basketball.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return service.selectNews(text, 0);
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
        return service.selectTech(text, 0);
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
        return service.selectCommon(text, 0);
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
}
