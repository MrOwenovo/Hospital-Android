package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Button btn_toReg, btn_login;
    private EditText autoid, username, password;
    private DatabaseHelper databaseHelper;
    private CheckBox cb_Remember;
    private User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.idnum);
        password = findViewById(R.id.psword);
        btn_login = findViewById(R.id.login);
        btn_toReg = findViewById(R.id.toReg);

        //按下login需要处理的事
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String psword = password.getText().toString();


                List<User> userList = databaseHelper.selectByUnameAndPsword(uname, psword);
                System.out.println(userList);

                if (!userList.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Successfully login！", Toast.LENGTH_LONG).show();

                    if (cb_Remember.isChecked()) {
                        SharedPreferences spf = getSharedPreferences("spfRecorid", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spf.edit();
                        edit.putString("idnum", uname);
                        edit.putString("password", psword);
                        edit.putBoolean("isRemember", true);
                        edit.apply();
                    } else {
                        SharedPreferences spf = getSharedPreferences("spfRecorid", MODE_PRIVATE);
                        SharedPreferences.Editor edit = spf.edit();
                        edit.putBoolean("isRemember", false);
                        edit.apply();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("idnum", uname);
                    intent.putExtra("id", u.getId());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect id number/password!", Toast.LENGTH_LONG).show();
                }
            }

        });

        btn_toReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
            }
        });
    }

}





