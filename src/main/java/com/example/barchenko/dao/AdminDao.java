package com.example.barchenko.dao;

import com.example.barchenko.entity.Admin;

public interface AdminDao {
    void createAdmin(Admin admin);
    void deleteRoom(Admin admin);
    Admin findAdminByEmail(String email);
}
