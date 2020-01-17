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

@WebServlet("/deleteRoom")
public class DeleteRoomController extends HttpServlet {

    private RoomDao roomDao = new JdbcRoomsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter("id"));
        Room room = roomDao.findRoomById(roomId);
        roomDao.deleteRoom(room);
        resp.sendRedirect("room.jsp");
    }
}
