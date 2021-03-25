package com.wt.basketball.controller;

import com.wt.basketball.common.AppException;
import com.wt.basketball.common.BizResult;
import com.wt.basketball.model.Article;
import com.wt.basketball.model.User;
import com.wt.basketball.model.vo.ArticleDetailVo;
import com.wt.basketball.model.vo.ArticleVo;
import com.wt.basketball.service.ArticleService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author wut
 * @since 2021-03-18
 */
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService service;

    @Resource
    private HttpServletRequest request;

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
        if (null == currentUser) {
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
    public BizResult add(Article article, HttpServletRequest request) {
        if (null == article || (!article.verify())) {
            return BizResult.fall("请填写标题/内容");
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }

        Integer add = service.add(article, currentUser);
        if (null != add) {
            return BizResult.succ(add);
        }
        return BizResult.FALL();
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

    /**
     * 点赞数
     * @param id
     * @return
     */
    @PostMapping("/good")
    public BizResult addGood(Integer id) {
        if (null == id) {
            return BizResult.fall("id不能为空");
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }

        if(service.addGood(id, currentUser)) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }

    @RequestMapping("/upload")
    public Map uploadImg(MultipartFile file) throws Exception{

        String filename = new Random().nextInt(10000) + file.getOriginalFilename();
        String saveFile = ResourceUtils.getURL("classpath:").getPath()+"static/img/" + filename;

        Map<String, Object> result = new HashMap<>();

        File dest = new File(saveFile);
        try {
            file.transferTo(dest);
            result.put("success", true);
            result.put("file_path", "/img/" + filename);
        } catch (IOException e) {
            result.put("success", false);
        }


        return result;
    }
}
