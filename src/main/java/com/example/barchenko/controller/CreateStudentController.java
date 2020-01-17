package com.example.barchenko.controller;

import com.example.barchenko.dao.StudentDao;
import com.example.barchenko.dao.impl.JdbcRoomsDao;
import com.example.barchenko.dao.impl.JdbcStudentsDao;
import com.example.barchenko.entity.Room;
import com.example.barchenko.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/create")
public class CreateStudentController extends HttpServlet {

    private StudentDao studentDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("createStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String group = req.getParameter("group");
        Room room = new JdbcRoomsDao().findRoomByNumber(
                Integer.parseInt(req.getParameter("room")));
        studentDao = new JdbcStudentsDao();
        List<Student> students = studentDao.findStudentsByRoomNumber(room.getNumber());
        Student student = new Student(fname, lname, age, gender, group, room);
        if (students.size() == room.getCapacity())
        {
            resp.sendRedirect("index.jsp");
            return;
        }
        studentDao.create(student);

        resp.sendRedirect("index.jsp");
    }

}
