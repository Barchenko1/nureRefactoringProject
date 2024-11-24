package com.example.barchenko.controller;

import com.example.barchenko.dao.AdminDao;
import com.example.barchenko.dao.impl.JdbcAdminDao;
import com.example.barchenko.entity.Admin;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private AdminDao adminDao = new JdbcAdminDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Admin adminExist = adminDao.findAdminByEmail(email);
        if (adminExist == null)
        {
            req.setAttribute("errorMessage", "Admin with this email doesn't exist.");
            req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
            return;
        }
        if (!BCrypt.checkpw(password, adminExist.getPassword())) {
            req.setAttribute("errorMessage", "Invalid password.");
            req.getRequestDispatcher("loginForm.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("loggedInAdmin", adminExist);

        session.setAttribute("isLoggedIn", true);
        resp.sendRedirect("index.jsp");
    }

}
