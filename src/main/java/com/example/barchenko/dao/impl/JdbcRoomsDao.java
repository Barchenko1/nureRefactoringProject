package com.example.barchenko.dao.impl;

import com.example.barchenko.dao.AbstractJdbcDao;
import com.example.barchenko.dao.EntityMapper;
import com.example.barchenko.dao.RoomDao;
import com.example.barchenko.entity.Room;
import com.example.barchenko.utils.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomsDao extends AbstractJdbcDao implements RoomDao {

    private static final String SQL_ROOM_FIND_BY_NUMBER = "SELECT * FROM Room "
            + "WHERE NUMBER = ?";

    private static final String SQL_ROOM_FIND_BY_ID = "SELECT * FROM Room "
            + "WHERE Id = ?";

    private static final String SQL_ROOM_FIND_ALL_ROOMS = "SELECT * FROM Room";

    private static final String SQL_ROOM_CREATE = "INSERT INTO Room " +
            "(number, capacity, flour) "
            + "VALUES (?,?,?)";
    private static final String SQL_ROOM_REMOVE = "DELETE FROM Room "
            + "WHERE number = ?";
    private static final String SQL_ROOM_UPDATE = "UPDATE Room SET "
            + "number = ?, capacity = ?, flour = ?  WHERE id = ?";

    @Override
    public void createRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("room is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ROOM_CREATE);
            int i = 1;
            ps.setInt(i++, room.getNumber());
            ps.setInt(i++, room.getCapacity());
            ps.setInt(i, room.getFlour());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("createRoom rollback room fail ", e1);
            }
            throw new RuntimeException("createRoom room fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public void updateRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("room is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ROOM_UPDATE);
            int i = 1;
            ps.setInt(i++, room.getNumber());
            ps.setInt(i++, room.getCapacity());
            ps.setInt(i++, room.getFlour());
            ps.setInt(i, room.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("updateRoom rollback student fail ",e1);
            }
            throw new RuntimeException("updateRoom student fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public void deleteRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("student is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ROOM_REMOVE);
            ps.setInt(1, room.getNumber());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("remove rollback room fail ",e1);
            }
            throw new RuntimeException("remove room fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public List<Room> findAllRooms() {
        Connection con = null;
        List<Room> rooms = new ArrayList<>();
        RoomMapper roomMapper = new RoomMapper();
        Statement st = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_ROOM_FIND_ALL_ROOMS);
            while (rs.next()) {
                rooms.add(roomMapper.mapRow(rs));
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("findAll rollback user fail ",e1);
            }
            throw new RuntimeException("findAll user fail ", e);
        } finally {
            closeResource(con, null, st, rs);
        }
        return rooms;
    }

    @Override
    public Room findRoomByNumber(int number) {
        if (number == 0) {
            throw new IllegalArgumentException();
        }
        Room room = new Room();
        JdbcRoomsDao.RoomMapper userMapper = new JdbcRoomsDao.RoomMapper();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ROOM_FIND_BY_NUMBER);
            ps.setInt(1, number);
            rs = ps.executeQuery();
            if (rs.next()) {
                room = userMapper.mapRow(rs);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException(
                        "findRoomByNumber rollback room fail ",e1);
            }
            throw new RuntimeException("findRoomByNumber room fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return room;
    }

    @Override
    public Room findRoomById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        Room room = new Room();
        JdbcRoomsDao.RoomMapper userMapper = new JdbcRoomsDao.RoomMapper();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ROOM_FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                room = userMapper.mapRow(rs);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException(
                        "findByLastName rollback room fail ",e1);
            }
            throw new RuntimeException("findByLogin room fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return room;
    }

    private static class RoomMapper implements EntityMapper<Room> {
        @Override public Room mapRow(ResultSet rs) {
            try {
                Room room = new Room();
                room.setId(rs.getInt(Fields.ID));
                room.setNumber(rs.getInt(Fields.ROOM_NUMBER));
                room.setCapacity(rs.getInt(Fields.ROOM_CAPACITY));
                room.setFlour(rs.getInt(Fields.ROOM_FLOUR));
                return room;
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
