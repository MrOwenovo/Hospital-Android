package com.example.myapplication.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_my_reservation, btn_online_inquiry, btn_appoint, btn_map, btn_health_education;
    private ImageView btn_logout;
    private TextView text_appointment,text_my_reservation,text_online_inquiry,text_map,text_health_education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_online_inquiry = findViewById(R.id.btn_online_inquiry);
        btn_appoint = findViewById(R.id.btn_appoint);
        btn_map = findViewById(R.id.btn_map);
        btn_health_education = findViewById(R.id.btn_health_education);
        btn_logout = findViewById(R.id.btn_logout);
        btn_my_reservation = findViewById(R.id.btn_my_reservation);
        text_appointment=findViewById(R.id.text_appointment);
        text_my_reservation=findViewById(R.id.text_my_reservation);
        text_online_inquiry=findViewById(R.id.text_online_inquiry);
        text_map=findViewById(R.id.text_map);
        text_health_education=findViewById(R.id.text_health_education);

        setListeners();

    }

    private void setListeners() {
        Onclick onclick = new Onclick();
        btn_my_reservation.setOnClickListener(onclick);
        btn_online_inquiry.setOnClickListener(onclick);
        btn_appoint.setOnClickListener(onclick);
        btn_map.setOnClickListener(onclick);
        btn_health_education.setOnClickListener(onclick);
        btn_logout.setOnClickListener(onclick);
        text_appointment.setOnClickListener(onclick);
        text_my_reservation.setOnClickListener(onclick);
        text_online_inquiry.setOnClickListener(onclick);
        text_map.setOnClickListener(onclick);
        text_health_education.setOnClickListener(onclick);

    }

    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            int vId=v.getId();
            if(vId==R.id.btn_my_reservation|| vId==R.id.text_my_reservation){
                intent = new Intent(MainActivity.this, MyReservationActivity.class);
            } else if (vId==R.id.btn_online_inquiry|| vId==R.id.text_online_inquiry) {
                intent = new Intent(MainActivity.this, OnlineInquiryActivity.class);
            } else if (vId==R.id.btn_appoint || vId==R.id.text_appointment) {
                intent = new Intent(MainActivity.this, AppointmentActivity.class);
            } else if (vId==R.id.btn_map|| vId==R.id.text_map) {
                intent = new Intent(MainActivity.this, MapActivity.class);
            } else if (vId==R.id.btn_health_education|| vId==R.id.text_health_education) {
                intent = new Intent(MainActivity.this, HealthEducationActivity.class);
            } else if (vId==R.id.btn_logout) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
            }
            startActivity(intent);
        }
    }
}