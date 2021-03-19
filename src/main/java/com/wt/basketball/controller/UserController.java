package com.wt.basketball.controller;

import com.wt.basketball.model.User;
import com.wt.basketball.service.UserService;
import com.wt.basketball.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/users")
    public List<User> users() {
        return service.search(null);
    }

    @PostMapping("/login")
    public boolean login(String username , String password, HttpServletRequest request) {
        User login = service.login(username, password);

        // 登录成功则添加session并返回true
        if (null != login) {
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
