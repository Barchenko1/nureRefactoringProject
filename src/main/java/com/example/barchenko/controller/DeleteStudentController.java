package com.example.barchenko.controller;

import com.example.barchenko.dao.StudentDao;
import com.example.barchenko.dao.impl.JdbcStudentsDao;
import com.example.barchenko.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteStudentController extends HttpServlet {

    private StudentDao studentDao = new JdbcStudentsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(req.getParameter("id"));
        Student student = studentDao.findStudentByID(studentId);
        studentDao.remove(student);
        resp.sendRedirect("index.jsp");
    }
}
