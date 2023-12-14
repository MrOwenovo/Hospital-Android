package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MapActivity extends AppCompatActivity {
    private ImageButton btn_indoor_map, btn_outdoor_map,btn_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        btn_outdoor_map = findViewById(R.id.btn_outdoor_map);
        btn_indoor_map = findViewById(R.id.btn_indoor_map);
        btn_back = findViewById(R.id.btn_back);
        setListeners();
    }
    private void setListeners() {
        Onclick onlick = new Onclick();

        btn_outdoor_map.setOnClickListener(onlick);
        btn_indoor_map.setOnClickListener(onlick);
        btn_back.setOnClickListener(onlick);
    }
    private class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = null;
            int vId=v.getId();
            if(vId==R.id.btn_outdoor_map){
                intent = new Intent(MapActivity.this, outdoormap.class);
            } else if (vId==R.id.btn_indoor_map) {
                intent = new Intent(MapActivity.this, indoormap2.class);
            }else if (vId==R.id.btn_back) {
                intent = new Intent(MapActivity.this, MainActivity.class);
            }
            startActivity(intent);
        }
    }
}