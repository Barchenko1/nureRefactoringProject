package com.example.barchenko.controller;

import com.example.barchenko.dao.RoomDao;
import com.example.barchenko.dao.impl.JdbcRoomsDao;
import com.example.barchenko.entity.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createRoom")
public class CreateRoomController extends HttpServlet {

    private RoomDao roomDao = new JdbcRoomsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("createRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int number = Integer.parseInt(req.getParameter("number"));
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        int flour = Integer.parseInt(req.getParameter("flour"));
        Room roomExist = roomDao.findRoomByNumber(number);
        if (roomExist.getNumber() != 0)
        {
            resp.sendRedirect("room.jsp");
            return;
        }
        Room room = new Room(number,capacity,flour);
        roomDao.createRoom(room);
        resp.sendRedirect("room.jsp");
    }

}
