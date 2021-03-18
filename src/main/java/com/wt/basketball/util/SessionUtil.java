package com.wt.basketball.util;

import com.wt.basketball.model.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    public static User getCurrentUser(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (null == user) {
            return null;
        }
        return (User)user;
    }

    public static void setCurrentUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }
}
