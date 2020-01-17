package com.example.barchenko.entity;

public class Student {
    private int id;
    private String fname;
    private String lname;
    private int age;
    private String gender;
    private String group;
    private Room room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student() {
    }

    public Student(String fname, String lname, int age, String gender, String group, Room room) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.gender = gender;
        this.group = group;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", group='" + group + '\'' +
                ", room=" + room +
                '}';
    }
}
