package com.company.resume.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutController",urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logout = request.getParameter("logout");
        if (logout.equals("Log Out")) {
            request.getSession().removeAttribute("loggedInUser");
            response.sendRedirect("login");
        }
    }
}
