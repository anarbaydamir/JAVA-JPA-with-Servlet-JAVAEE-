package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.http.*;

public class UserRequestUtil {
    public static void checkRequest (HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException {
        String userIdStr = request.getParameter("userId");
        if (userIdStr==null || userIdStr.trim().isEmpty()) {
         //   request.setAttribute("msg","specify id");
            throw new IllegalArgumentException("id is not specified");
        }
    }

    public static User processRequest(HttpServletRequest request,HttpServletResponse response) {
        UserRequestUtil.checkRequest(request, response);


        Integer userId = Integer.parseInt(request.getParameter("userId"));
        UserDaoInter userDaoInter = Context.instanceUserDao();
        User u = userDaoInter.getById(userId);

        if (u == null) {
            throw new IllegalArgumentException("There is no user with this id");
        }

        return  u;
    }
}
