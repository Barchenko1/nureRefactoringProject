package com.example.barchenko.dao.impl;

import com.example.barchenko.dao.AbstractJdbcDao;
import com.example.barchenko.dao.EntityMapper;
import com.example.barchenko.dao.StudentDao;
import com.example.barchenko.entity.Room;
import com.example.barchenko.entity.Student;
import com.example.barchenko.utils.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentsDao extends AbstractJdbcDao implements StudentDao {

    private static final String SQL_STUDENT_CREATE = "INSERT INTO Student " +
            "(fname, lname, age, gender, `group`, Room_id) "
            + "VALUES (?,?,?,?,?,?)";
    private static final String SQL_STUDENT_UPDATE = "UPDATE Student SET "
            + "fname = ?, lname = ?, age = ?, "
            + "gender = ?, `group` = ?, Room_id = ?  WHERE id = ?";

    private static final String SQL_STUDENT_REMOVE = "DELETE FROM Student "
            + "WHERE id = ?";
    private static final String SQL_STUDENT_FIND_ALL = "SELECT * FROM Student";
    private static final String SQL_STUDENT_FIND_BY_LNAME = "SELECT * FROM Student "
            + "WHERE LNAME = ?";
    private static final String SQL_STUDENT_FIND_ALL_BY_ROOM_NUMBER = "SELECT * FROM Student JOIN Room on Student.Room_id = Room.id WHERE number = ?;";
    private static final String SQL_STUDENT_FIND_ALL_BY_Id = "SELECT * FROM Student WHERE id = ?";

    @Override
    public void create(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_CREATE);
            int i = 1;
            ps.setString(i++, student.getFname());
            ps.setString(i++, student.getLname());
            ps.setInt(i++, student.getAge());
            ps.setString(i++, student.getGender());
            ps.setString(i++, student.getGroup());
            ps.setInt(i, student.getRoom().getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("findById rollback student fail ", e1);
            }
            throw new RuntimeException("findById student fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }

    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_UPDATE);
            int i = 1;
            ps.setString(i++, student.getFname());
            ps.setString(i++, student.getLname());
            ps.setInt(i++, student.getAge());
            ps.setString(i++, student.getGender());
            ps.setString(i++, student.getGroup());
            ps.setInt(i++, student.getRoom().getId());
            ps.setInt(i, student.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("update rollback student fail ",e1);
            }
            throw new RuntimeException("update student fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public void remove(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_REMOVE);
            ps.setInt(1, student.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("remove rollback student fail ",e1);
            }
            throw new RuntimeException("remove student fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public List<Student> findAllStudents() {
        Connection con = null;
        List<Student> userList = new ArrayList<>();
        StudentMapper userMapper = new StudentMapper();
        Statement st = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_STUDENT_FIND_ALL);
            while (rs.next()) {
                userList.add(userMapper.mapRow(rs));
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
        return userList;
    }

    @Override
    public Student findByLastName(String lname) {
        if (lname == null) {
            throw new IllegalArgumentException();
        }
        Student student = new Student();
        StudentMapper userMapper = new StudentMapper();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_FIND_BY_LNAME);
            ps.setString(1, lname);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = userMapper.mapRow(rs);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException(
                        "findByLastName rollback student fail ",e1);
            }
            throw new RuntimeException("findByLogin student fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return student;
    }

    @Override
    public List<Student> findStudentsByRoomNumber(int number) {
        Connection con = null;
        List<Student>  students = new ArrayList<>();
        StudentMapper userMapper = new StudentMapper();
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_FIND_ALL_BY_ROOM_NUMBER);
            ps.setInt(1, number);
            rs = ps.executeQuery();
            while (rs.next()) {
                students.add(userMapper.mapRow(rs));
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("findStudentsByRoomId rollback user fail ",e1);
            }
            throw new RuntimeException("findStudentsByRoomId user fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return students;
    }

    @Override
    public Student findStudentByID(int id) {
        Connection con = null;
        Student student = new Student();
        StudentMapper userMapper = new StudentMapper();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_STUDENT_FIND_ALL_BY_Id);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                student = userMapper.mapRow(rs);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("findStudentsByRoomId rollback user fail ",e1);
            }
            throw new RuntimeException("findStudentsByRoomId user fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return student;
    }


    private static class StudentMapper implements EntityMapper<Student> {
        @Override public Student mapRow(ResultSet rs) {
            try {
                Student student = new Student();
                student.setId(rs.getInt(Fields.STUDENT_ID));
                student.setFname(rs.getString(Fields.STUDENT_FNAME));
                student.setLname(rs.getString(Fields.STUDENT_LNAME));
                student.setAge(rs.getInt(Fields.STUDENT_AGE));
                student.setGender(rs.getString(Fields.STUDENT_GENDER));
                student.setGroup(rs.getString(Fields.STUDENT_GROUP));
                int roomId = rs.getInt(Fields.STUDENT_ROOM_ID);
                Room room = new JdbcRoomsDao().findRoomById(roomId);
                student.setRoom(room);
                return student;
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
