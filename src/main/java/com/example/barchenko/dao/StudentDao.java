package com.example.barchenko.dao;

import com.example.barchenko.entity.Student;
import java.util.List;

public interface StudentDao {
    void create(Student student);
    void update(Student student);
    void remove(Student student);
    List<Student> findAllStudents();
    Student findByLastName(String lname);
    List<Student> findStudentsByRoomNumber(int number);
    Student findStudentByID(int id);
}
