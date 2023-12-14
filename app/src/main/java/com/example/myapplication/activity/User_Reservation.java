package com.example.myapplication.activity;

public class User_Reservation {
    private int id;
    private String user_id_num;
    private String username;
    private String date;
    private String time_slot;
    private String department;
    private String doctor;

    public User_Reservation(int id, String user_id_num, String username, String date, String time_slot, String department, String doctor) {
        this.id = id;
        this.user_id_num = user_id_num;
        this.username = username;
        this.date = date;
        this.time_slot = time_slot;
        this.department = department;
        this.doctor = doctor;
    }

    public User_Reservation(String user_id_num, String username, String date, String time_slot, String department, String doctor) {
        this.user_id_num = user_id_num;
        this.username = username;
        this.date = date;
        this.time_slot = time_slot;
        this.department = department;
        this.doctor = doctor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id_num() {
        return user_id_num;
    }

    public void setUser_id_num(String user_id_num) {
        this.user_id_num = user_id_num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "User_Reservation{" +
                "id=" + id +
                ", user_id_num='" + user_id_num + '\'' +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", time_slot='" + time_slot + '\'' +
                ", department='" + department + '\'' +
                ", doctor='" + doctor + '\'' +
                '}';
    }


}
