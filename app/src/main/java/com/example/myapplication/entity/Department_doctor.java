package com.example.myapplication.entity;

public class Department_doctor {
    private int id;
    private String department;
    private String doctor;

    public Department_doctor(int id, String department, String doctor) {
        this.id = id;
        this.department = department;
        this.doctor = doctor;
    }

    public Department_doctor(String department, String doctor) {
        this.department = department;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Department_doctor{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", doctor='" + doctor + '\'' +
                '}';
    }
}
