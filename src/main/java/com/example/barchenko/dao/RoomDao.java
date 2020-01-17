package com.example.barchenko.dao;

import com.example.barchenko.entity.Room;
import com.example.barchenko.entity.Student;

import java.util.List;

public interface RoomDao {
    void createRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(Room room);
    List<Room> findAllRooms();
    Room findRoomByNumber(int number);
    Room findRoomById(int id);
}
