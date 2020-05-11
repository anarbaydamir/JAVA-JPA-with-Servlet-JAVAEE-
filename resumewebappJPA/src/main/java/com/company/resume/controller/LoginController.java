/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import com.company.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author qwant
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDaoInter userDaoInter = Context.instanceUserDao();
    private BCrypt.Verifyer verifyer = BCrypt.verifyer();// crypti yoxlamaq uchun dependency

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User u = userDaoInter.getUserByEmail(email);

            if (u == null) {
                throw new IllegalArgumentException("User doesn't exist");
            }
            BCrypt.Result result = verifyer.verify(password.toCharArray(),u.getPassword());
            if (!result.verified) {
                throw new IllegalArgumentException("Password is incorrect");
            }
            request.getSession().setAttribute("loggedInUser",u);
            response.sendRedirect("users");
        }
        catch (Exception ex) {
            ControllerUtil.errorPage(response,ex);
        }
    }
}
