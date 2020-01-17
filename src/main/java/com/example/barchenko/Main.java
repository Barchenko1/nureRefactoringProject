package com.example.barchenko;

import com.example.barchenko.dao.RoomDao;
import com.example.barchenko.dao.StudentDao;
import com.example.barchenko.dao.impl.JdbcRoomsDao;
import com.example.barchenko.dao.impl.JdbcStudentsDao;
import com.example.barchenko.entity.Room;
import com.example.barchenko.entity.Student;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello project!");
        StudentDao jdbcStudentsDao = new JdbcStudentsDao();
        RoomDao roomDao = new JdbcRoomsDao();
        Student s = jdbcStudentsDao.findByLastName("Tata");
//        List<Student> students = jdbcStudentsDao.findAllStudents(); done
//        List<Room> rooms = roomDao.findAllRooms(); done
//        Room room = roomDao.findRoomByNumber(101); done
        Room room = new Room(102,4,1,1);
//        roomDao.createRoom(room);
        System.out.println();
       // jdbcStudentsDao.create(s); done
//        jdbcStudentsDao.remove(s); dobe

    }
}
