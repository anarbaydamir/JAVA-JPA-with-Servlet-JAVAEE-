/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author qwant
 */
@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {
    
    private UserDaoInter userDaoInter = Context.instanceUserDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


       int id = Integer.valueOf(request.getParameter("id"));
       String action = request.getParameter("action");

       if (action.equals("update")) {
           String name = String.valueOf(request.getParameter("name"));
           String surname = String.valueOf(request.getParameter("surname"));
           String email = String.valueOf(request.getParameter("email"));
           String phone = String.valueOf(request.getParameter("phone"));
           User u = userDaoInter.getById(id);
           u.setName(name);
           u.setSurname(surname);
           u.setEmail(email);
           u.setPhoneNumber(phone);

           userDaoInter.updateUser(u);
       }
       else if (action.equals("delete")) {
           userDaoInter.removeUser(id);
       }
       response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("userId");
            if (userIdStr==null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }
            Integer userId = Integer.parseInt(userIdStr);
            UserDaoInter userDaoInter = Context.instanceUserDao();
            User u = userDaoInter.getById(userId);

            if (u == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }

            request.setAttribute("user",u);
            request.getRequestDispatcher("userdetail.jsp").forward(request,response);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }
    }
}
