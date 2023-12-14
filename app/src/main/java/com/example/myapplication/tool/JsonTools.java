package com.example.myapplication.tool;

import android.content.Context;

import com.example.myapplication.R;
import com.example.myapplication.entity.Clinic;
import com.example.myapplication.entity.Disease;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonTools {

    public static List<Clinic> loadClinicsFromJson(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.disease);
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            Type type = new TypeToken<List<Clinic>>() {}.getType();
            List<Clinic> clinics = (List<Clinic>)gson.fromJson(reader, type);
            System.out.println(1);
            System.out.println(clinics);
            return clinics;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static Map<String, Set<String>> loadDiseasesWithSymptomsFromJson(Context context) {
        List<Clinic> clinics = loadClinicsFromJson(context);
        Map<String, Set<String>> diseasesWithSymptoms = new HashMap<>();
        for (Clinic clinic : clinics) {
            for (Disease disease : clinic.getDiseases()) {
                diseasesWithSymptoms.put(disease.getName(), new HashSet<>(disease.getSymptoms()));
            }
        }
        return diseasesWithSymptoms;
    }

    public static Map<String, Map<String, Set<String>>> loadClinicsWithDiseasesAndSymptomsFromJson(Context context) {
        List<Clinic> clinics = loadClinicsFromJson(context);
        Map<String, Map<String, Set<String>>> clinicsWithDiseases = new HashMap<>();
        for (Clinic clinic : clinics) {
            Map<String, Set<String>> diseasesWithSymptoms = new HashMap<>();
            for (Disease disease : clinic.getDiseases()) {
                diseasesWithSymptoms.put(disease.getName(), new HashSet<>(disease.getSymptoms()));
            }
            clinicsWithDiseases.put(clinic.getClinic(), diseasesWithSymptoms);
        }
        return clinicsWithDiseases;
    }

    public static Set<String> loadSymptomsFromJson(Context context) {
        List<Clinic> clinics = loadClinicsFromJson(context);
        Set<String> knownSymptoms = new HashSet<>();
        for (Clinic clinic : clinics) {
            for (Disease disease : clinic.getDiseases()) {
                knownSymptoms.addAll(disease.getSymptoms());
            }
        }
        return knownSymptoms;
    }
}
