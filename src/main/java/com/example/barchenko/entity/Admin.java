package com.example.barchenko.entity;

public class Admin {
    private int id;
    private String email;
    private String password;

    public Admin(int id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
