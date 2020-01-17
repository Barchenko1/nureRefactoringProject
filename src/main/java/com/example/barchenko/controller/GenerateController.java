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

@WebServlet("/generate")
public class GenerateController extends HttpServlet {
    private StudentDao studentDao = new JdbcStudentsDao();
    private RoomDao roomDao = new JdbcRoomsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Room> rooms = roomDao.findAllRooms();
        Student newStudent = new Student("newStudent", "newStudent", 0, "", "", null);
        for (Room room:rooms) {
            List<Student> studentsInRoom = studentDao.findStudentsByRoomNumber(room.getNumber());
            if (studentsInRoom.size() != room.getCapacity())
            {
                newStudent.setRoom(room);
            }
        }
        if (newStudent.getRoom() == null)
        {
            resp.sendRedirect("index.jsp");
            return;
        }
        studentDao.create(newStudent);
        resp.sendRedirect("index.jsp");
    }
}
