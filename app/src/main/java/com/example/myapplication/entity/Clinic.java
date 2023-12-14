package com.example.myapplication.entity;

import java.util.List;

public class Clinic {
    private String clinic; // 修改为与 JSON 中的键匹配
    private List<Disease> diseases;

    public Clinic(String clinic, List<Disease> diseases) {
        this.clinic = clinic;
        this.diseases = diseases;
    }

    public String getClinic() {
        return clinic; // 修改 getter 方法
    }

    public List<Disease> getDiseases() {
        return diseases;
    }
}
