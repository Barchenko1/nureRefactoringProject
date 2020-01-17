package com.example.barchenko.controller;

import com.example.barchenko.dao.RoomDao;
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

import java.util.List;


@WebServlet("/update")
public class UpdateStudentController extends HttpServlet {

    private StudentDao studentDao = new JdbcStudentsDao();
    private RoomDao roomDao = new JdbcRoomsDao();

    @Override protected void doGet(HttpServletRequest req,
                                   HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDao.findStudentByID(id);

        req.setAttribute("fname", student.getFname());
        req.setAttribute("lname", student.getLname());
        req.setAttribute("age", student.getAge());
        req.setAttribute("gender", student.getGender());
        req.setAttribute("group", student.getGroup());
        req.setAttribute("room", student.getRoom().getNumber());

        req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        int age = Integer.parseInt(req.getParameter("age"));
        String gender = req.getParameter("gender");
        String group = req.getParameter("group");
        Room room = roomDao.findRoomByNumber(
                Integer.parseInt(req.getParameter("room")));
        List<Student> students = studentDao.findStudentsByRoomNumber(room.getNumber());
        Student student = new Student(fname, lname, age, gender, group, room);
        if (students.size() > room.getCapacity())
        {
            resp.sendRedirect("index.jsp");
            return;
        }
        student.setId(id);
        studentDao.update(student);

        resp.sendRedirect("index.jsp");
    }
}