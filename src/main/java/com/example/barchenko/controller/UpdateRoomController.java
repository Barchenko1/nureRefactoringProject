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


@WebServlet("/updateRoom")
public class UpdateRoomController extends HttpServlet {

    private RoomDao roomDao = new JdbcRoomsDao();

    @Override protected void doGet(HttpServletRequest req,
                                   HttpServletResponse resp) throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter("id"));
        Room room = roomDao.findRoomById(roomId);

        req.setAttribute("number", room.getNumber());
        req.setAttribute("capacity", room.getCapacity());
        req.setAttribute("flour", room.getFlour());
        
        req.getRequestDispatcher("editRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int number = Integer.parseInt(req.getParameter("number"));
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        int flour = Integer.parseInt(req.getParameter("flour"));
        int roomId = roomDao.findRoomByNumber(number).getId();
        Room room = new Room(number, capacity, flour);
        if (roomId != 0)
        {
            resp.sendRedirect("room.jsp");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
        room.setId(id);
        roomDao.updateRoom(room);
        resp.sendRedirect("room.jsp");
    }
}