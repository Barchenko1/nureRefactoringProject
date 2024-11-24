package com.example.barchenko.controller;

import com.example.barchenko.dao.AdminDao;
import com.example.barchenko.dao.RoomDao;
import com.example.barchenko.dao.impl.JdbcAdminDao;
import com.example.barchenko.dao.impl.JdbcRoomsDao;
import com.example.barchenko.entity.Admin;
import com.example.barchenko.entity.Room;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createAdmin")
public class CreateAdminController extends HttpServlet {

    private AdminDao adminDao = new JdbcAdminDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("createAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        Admin adminExist = adminDao.findAdminByEmail(email);
        if (!password.equals(cpassword)) {
            resp.sendRedirect("createAdmin.jsp");
            return;
        }
        if (adminExist != null)
        {
            resp.sendRedirect("createAdmin.jsp");
            return;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        Admin admin = new Admin(email,hashedPassword);
        adminDao.createAdmin(admin);
        resp.sendRedirect("loginForm.jsp");
    }

}
