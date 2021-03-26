package com.wt.basketball.controller;

import com.wt.basketball.common.AppException;
import com.wt.basketball.common.BizResult;
import com.wt.basketball.model.User;
import com.wt.basketball.service.UserService;
import com.wt.basketball.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Resource
    private HttpServletRequest request;

    /**
     * 用户列表（只有管理员才可以看）
     * @return
     */
    @GetMapping("/users")
    public List<User> users() {
        // 只有管理员才能操作
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser || currentUser.getIsadmin() != 1) {
            return null;
        }


        return service.search();
    }

    /**
     * 我的球友
     * @return
     */
    @GetMapping("/ballFriends")
    public List<User> ballFriends() {
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return null;
        }

        return service.myFriend(currentUser.getUsername());
    }

    @PostMapping("/login")
    public boolean login(String username , String password, HttpServletRequest request) {
        User login = service.login(username, password);

        // 登录成功则添加session并返回true
        if (null != login) {
            logger.info("用户登录：{}, 登录ip：{}" ,username, request.getRemoteAddr());
            request.getSession().setAttribute("user", login);
            return true;
        }

        return false;
    }

    /**
     * 获取当前登录者
     * @param request
     * @return
     */
    @GetMapping("/current")
    public User current(HttpServletRequest request) {
        User current = (User) request.getSession().getAttribute("user");

        return current;
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return true;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PutMapping("/register")
    public BizResult register(User user) {
        if (null == user) {
            return BizResult.fall("不存在");
        }
       if (StringUtils.isEmpty(user.getUsername())) {
           return BizResult.fall("用户名不能为空");
       }
       if (StringUtils.isEmpty(user.getNickname())) {
           return BizResult.fall("昵称不能为空");
       }
       if (StringUtils.isEmpty(user.getPassword())) {
           return BizResult.fall("密码不能为空");
       }
       if (StringUtils.isEmpty(user.getEmail())) {
           return BizResult.fall("邮箱不能为空");
       }
       if (StringUtils.isEmpty(user.getPhone())) {
           return BizResult.fall("手机号不能为空");
       }

       return BizResult.succ(service.register(user));
    }

    /**
     * 添加用户(管理员才能用)
     * @param user
     * @return
     */
    @PutMapping("/user")
    public BizResult add(User user) {
        // 只有管理员才能操作
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }
        if (currentUser.getIsadmin() != 1) {
            return BizResult.fall("你不是管理员");
        }


        if (null == user) {
            return BizResult.fall("不存在");
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            return BizResult.fall("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getNickname())) {
            return BizResult.fall("昵称不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return BizResult.fall("密码不能为空");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            return BizResult.fall("邮箱不能为空");
        }
        if (StringUtils.isEmpty(user.getPhone())) {
            return BizResult.fall("手机号不能为空");
        }
        if (null == user.getIsadmin()) {
            return BizResult.fall("角色不能为空");
        }


        boolean result = service.add(user);
        if (result) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }

     /**
     * 编辑用户（管理员才能用）
     * @param user
     * @return
     */
    @PostMapping("/user")
    public BizResult update(User user) {
        // 只有管理员才能操作
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }
        if (currentUser.getIsadmin() != 1) {
            return BizResult.fall("你不是管理员");
        }

        if (StringUtils.isEmpty(user.getUsername())) {
            return BizResult.fall("用户名不能为空");
        }


        boolean update = service.update(user);
        if (update) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }

    /**
     * 删除用户（管理员才能用）
     * @param username
     * @return
     */
    @DeleteMapping("/user")
    public BizResult delete(String username) {
        // 只有管理员才能操作
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return BizResult.fall("请登录");
        }
        if (currentUser.getIsadmin() != 1) {
            return BizResult.fall("你不是管理员");
        }


        if (StringUtils.isEmpty(username)) {
            return BizResult.fall("用户名不能为空");
        }

        boolean delete = service.delete(username);
        if (delete) {
            return BizResult.SUCC();
        }
        return BizResult.FALL();
    }



    /**
     * 更新
     * @param user
     * @return
     */
    @PostMapping("/update")
    public boolean update(User user, HttpServletRequest request) {
        // 校验
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }
        if (currentUser.getIsadmin() == 0 && (!currentUser.getUsername().equals(user.getUsername()))) {
            return false;
        }

        service.update(user);

        SessionUtil.setCurrentUser(request, service.get(user.getUsername()));

        return true;
    }

    /**
     * 更新密码
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/updatePassword")
    public boolean updatePassword(String username, String password, HttpServletRequest request) {
        // 校验
        User currentUser = SessionUtil.getCurrentUser(request);
        if (null == currentUser) {
            return false;
        }
        if (currentUser.getIsadmin() == 0 && (!currentUser.getUsername().equals(username))) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return service.update(user);
    }
}
