package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class HealthEducationActivity extends AppCompatActivity {


    private Button btn_ht1,btn_pr2,btn_ap3,btn_ah4;
    private ImageButton btn_back;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_education);
        btn_ht1 = findViewById(R.id.btn_ht1);
        btn_pr2 = findViewById(R.id.btn_pr2);
        btn_ap3 = findViewById(R.id.btn_ap3);
        btn_ah4 = findViewById(R.id.btn_ah4);
        btn_back = findViewById(R.id.btn_back);
        setListeners();
    }
    private void setListeners() {
        Onclick onlick = new Onclick();

        btn_ht1.setOnClickListener(onlick);
        btn_pr2.setOnClickListener(onlick);
        btn_ap3.setOnClickListener(onlick);
        btn_ah4.setOnClickListener(onlick);
        btn_back.setOnClickListener(onlick);
    }
    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            int vId=v.getId();
            if(vId== R.id.btn_ht1){
                intent = new Intent(HealthEducationActivity.this, Healthtips.class);
            } else if (vId== R.id.btn_pr2) {
                intent = new Intent(HealthEducationActivity.this, Postoperativerecovery.class);
            }else if (vId== R.id.btn_ap3) {
                intent = new Intent(HealthEducationActivity.this, AIDSprevention.class);
            }else if (vId== R.id.btn_ah4) {
                intent = new Intent(HealthEducationActivity.this, Adolescenthealth.class);
            }else if (vId== R.id.btn_back) {
                intent = new Intent(HealthEducationActivity.this, MainActivity.class);
            }
            startActivity(intent);
        }
    }
}