package com.example.barchenko.dao.impl;

import com.example.barchenko.dao.AbstractJdbcDao;
import com.example.barchenko.dao.AdminDao;
import com.example.barchenko.dao.EntityMapper;
import com.example.barchenko.entity.Admin;
import com.example.barchenko.entity.Room;
import com.example.barchenko.utils.Fields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAdminDao extends AbstractJdbcDao implements AdminDao {

    private static final String SQL_ADMIN_CREATE = "INSERT INTO Admin " +
            "(email, password) "
            + "VALUES (?,?)";
    private static final String SQL_ADMIN_REMOVE = "DELETE FROM Admin "
            + "WHERE email = ?";
    private static final String SQL_ADMIN_FIND_BY_EMAIL = "SELECT * FROM Admin "
            + "WHERE email = ?";

    @Override
    public void createAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("admin is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ADMIN_CREATE);
            int i = 1;
            ps.setString(i++, admin.getEmail());
            ps.setString(i, admin.getPassword());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("createAdmin rollback room fail ", e1);
            }
            throw new RuntimeException("createAdmin room fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public void deleteRoom(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("admin is null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ADMIN_REMOVE);
            ps.setString(1, admin.getEmail());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException("remove rollback admin fail ",e1);
            }
            throw new RuntimeException("remove admin fail ", e);
        } finally {
            closeResource(con, ps, null, null);
        }
    }

    @Override
    public Admin findAdminByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException();
        }
        Admin admin = null;
        AdminMapper adminMapper = new AdminMapper();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = createConnection();
            ps = con.prepareStatement(SQL_ADMIN_FIND_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = adminMapper.mapRow(rs);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.addSuppressed(e);
                throw new RuntimeException(
                        "findAdminByEmail rollback admin fail ", e1);
            }
            throw new RuntimeException("findAdminByEmail admin fail ", e);
        } finally {
            closeResource(con, ps, null, rs);
        }
        return admin;
    }

    private static class AdminMapper implements EntityMapper<Admin> {
        @Override public Admin mapRow(ResultSet rs) {
            try {
                Admin admin = new Admin();
                admin.setId(rs.getInt(Fields.ID));
                admin.setEmail(rs.getString(Fields.ADMIN_EMAIL));
                admin.setPassword(rs.getString(Fields.ADMIN_PASSWORD));
                return admin;
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
