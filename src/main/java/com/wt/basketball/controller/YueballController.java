package com.wt.basketball.controller;

import com.wt.basketball.common.AppException;
import com.wt.basketball.common.BizResult;
import com.wt.basketball.model.User;
import com.wt.basketball.model.Yueball;
import com.wt.basketball.model.vo.YueballDetailVo;
import com.wt.basketball.model.vo.YueballVo;
import com.wt.basketball.service.YueballService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RequestMapping("/yue")
@RestController
public class YueballController {

    @Autowired
    private YueballService service;

    @Resource
    private HttpServletRequest request;

    /**
     * 查询
     * @param text
     * @return
     */
    @GetMapping("/list")
    public List<YueballVo> select(String text) {
        return service.select(text, null);
    }

    /**
     * 热门
     * @return
     */
    @GetMapping("/hot")
    public List<YueballVo> selectHot() {
        return service.select(null, 1);
    }

    /**
     * 约球详情
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public YueballDetailVo detail(Integer id) {
        if (null == id) {
            return null;
        }

        return service.getYueballDetail(id);
    }

    /**
     * 更新
     * @param yueball
     * @return
     */
    @PostMapping("/yueball")
    public boolean update(Yueball yueball){
        if (StringUtils.isEmpty(yueball.getId())) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }

        return service.update(yueball, currentUser);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/yueball")
    public boolean delete(Integer id) {
        if (null == id) {
            return false;
        }

        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }
        return service.delete(id, currentUser);
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("/add")
    public void add(String name, String placemsg, MultipartFile img, HttpServletResponse response) throws Exception {
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return ;
//            return BizResult.fall("请登录后再添加");
        }

        if (null == img) {
            return ;
//            return BizResult.fall("图片不能为空");
        }
        if(StringUtils.isEmpty(name)) {
            return ;
//            return BizResult.fall("球场名不能为空");
        }
        if (StringUtils.isEmpty(placemsg)) {
            return ;
//            return BizResult.fall("球场详情不能为空");
        }

        String filename = new Random().nextInt(10000) + img.getOriginalFilename();
        String saveFile = ResourceUtils.getURL("classpath:").getPath()+"static/img/" + filename;

        File dest = new File(saveFile);
        try {
            img.transferTo(dest);
        } catch (IOException e) {
            throw new AppException("文件保存失败");
        }

        boolean add = service.add(new Yueball(name, placemsg, "/img/" + filename), currentUser);
        if (add) {
            response.sendRedirect("/yueballpage.html");
        }
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping("/update")
    public void update(Integer id, String name, String placemsg, MultipartFile img, HttpServletResponse response) throws Exception {
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == id) {
            return;
        }
        if (null == currentUser) {
            return ;
//            return BizResult.fall("请登录后再添加");
        }
        if(StringUtils.isEmpty(name)) {
            return ;
//            return BizResult.fall("球场名不能为空");
        }
        if (StringUtils.isEmpty(placemsg)) {
            return ;
//            return BizResult.fall("球场详情不能为空");
        }

        Yueball yueball = new Yueball();
        yueball.setId(id);
        yueball.setName(name);
        yueball.setPlacemsg(placemsg);

        if (null != img &&  (!"".equals(img.getOriginalFilename()))) {
            String filename = new Random().nextInt(10000) + img.getOriginalFilename();
            String saveFile = ResourceUtils.getURL("classpath:").getPath() + "static/img/" + filename;

            File dest = new File(saveFile);
            try {
                img.transferTo(dest);
            } catch (IOException e) {
                throw new AppException("文件保存失败");
            }

            yueball.setImgsrc("/img/" + filename);
        }

        boolean add = service.update(yueball, currentUser);
        if (add) {
            response.sendRedirect("/yuedetail.html?id=" + id);
        }
    }

    /**
     * 约球
     * @param id
     * @return
     */
    @PutMapping("/yue")
    public BizResult yue(Integer id) {
        if (null == id) {
            return BizResult.fall("球场id不能为空");
        }

        return BizResult.succ(service.yue(id));
    }

    /**
     * 取消约球
     * @param id
     * @return
     */
    @DeleteMapping("/yue")
    public BizResult unYue(Integer id) {
        if (null == id) {
            return BizResult.fall("球场id不能为空");
        }

        return BizResult.succ(service.unYue(id));
    }
}
