package com.example.myapplication.tool;

import com.example.myapplication.entity.Clinic;
import com.example.myapplication.entity.Disease;

public class DiseaseMatch {
    private Disease disease;
    private Clinic clinic;

    public DiseaseMatch(Disease disease, Clinic clinic) {
        this.disease = disease;
        this.clinic = clinic;
    }

    public Disease getDisease() {
        return disease;
    }

    public Clinic getClinic() {
        return clinic;
    }
}

