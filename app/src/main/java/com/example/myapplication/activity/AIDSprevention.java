package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AIDSprevention extends AppCompatActivity {

    private ImageButton btn_back;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidsprevention);
        btn_back = findViewById(R.id.btn_back);
        setListeners();
    }
    private void setListeners() {
        Onclick onlick = new Onclick();

        btn_back.setOnClickListener(onlick);
    }
    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            int vId=v.getId();
            if (vId== R.id.btn_back) {
                intent = new Intent(AIDSprevention.this, HealthEducationActivity.class);
            }
            startActivity(intent);
        }
    }
}