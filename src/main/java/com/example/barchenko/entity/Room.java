package com.example.barchenko.entity;

public class Room {
    private int id;
    private int number;
    private int capacity;
    private int flour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFlour() {
        return flour;
    }

    public void setFlour(int flour) {
        this.flour = flour;
    }

    public Room() {
    }

    public Room(int id, int number, int capacity, int flour) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.flour = flour;
    }

    public Room(int number, int capacity, int flour) {
        this.number = number;
        this.capacity = capacity;
        this.flour = flour;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", capacity=" + capacity +
                ", flour=" + flour +
                '}';
    }
}
