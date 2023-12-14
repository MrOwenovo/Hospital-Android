package com.example.myapplication.entity;

import java.util.HashSet;
import java.util.Set;

public class Disease {
    private String name;
    private Set<String> symptoms;


    public Disease(String name, Set<String> symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymptoms(Set<String> symptoms) {
        this.symptoms = symptoms;
    }



    // getter方法
    public String getName() {
        return name;
    }


    public Set<String> getSymptoms() {
        return symptoms;
    }

    // 匹配症状的方法
    public int matchSymptoms(Set<String> userSymptoms) {
        // 计算交集的大小作为匹配度
        Set<String> intersection = new HashSet<>(symptoms);
        intersection.retainAll(userSymptoms);
        return intersection.size();
    }
}
