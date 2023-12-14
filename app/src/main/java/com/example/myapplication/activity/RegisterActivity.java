package com.example.myapplication.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.R;

import java.util.List;

   public class RegisterActivity extends AppCompatActivity {
       private EditText idn, namee, psword, pswordc, tel;
       private Button btnRegister;
       private DatabaseHelper databaseHelper;

       SQLiteDatabase db;


       //跳转回登录页面
       public void goback(View view) {
           Intent btl = new Intent(RegisterActivity.this, LoginActivity.class);
           startActivity(btl);
       }

       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_register);

           //databaseHelper = new DatabaseHelper(this);
           btnRegister = findViewById(R.id.register);
           idn = findViewById(R.id.username);
           psword = findViewById(R.id.password);
           pswordc = findViewById(R.id.passwordcon);
           tel = findViewById(R.id.phone);
           setListeners();

       }

       private void setListeners() {
           Onclick onlick = new Onclick();
           btnRegister.setOnClickListener(onlick);
       }

       private class Onclick implements View.OnClickListener {
           @Override
           public void onClick(View v) {
               Intent intent = null;
               String idnum = idn.getText().toString().trim();
               String name = namee.getText().toString();
               String password = psword.getText().toString().trim();
               String passConfirm = pswordc.getText().toString();
               String phone = tel.getText().toString();
               if (TextUtils.isEmpty(idnum)) {
                   //ContentValues是一种基本的存储类型
                   ContentValues cv = new ContentValues();
                   //放入数据
                   cv.put("usname",name);
                   cv.put("uspwd",password);
                   db.insert("logins",null,cv);
                   //从当前界面跳转到登录页面
                   Intent intent1 = new Intent();
                   intent.setClass(RegisterActivity.this,LoginActivity.class);
                   startActivity(intent1);
                   //弹窗
                   Toast.makeText(getApplicationContext(),"账号注册成功！！",Toast.LENGTH_SHORT).show();

                   Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
                   return;
               }
               //databaseHelper = new DatabaseHelper();

//        查询这个账号是否已经被注册
               List<User> userList = databaseHelper.selectByUname(idnum);
               if (!userList.isEmpty()) {  //说明已经被注册过了
                   Toast.makeText(RegisterActivity.this, "Already registered", Toast.LENGTH_LONG).show();
                   return;
               }
               if (TextUtils.isEmpty(name)) {
                   Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
               }
               if (TextUtils.isEmpty(password)) {
                   Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
                   return;
               }
               if (!TextUtils.equals(password, passConfirm)) {
                   Toast.makeText(RegisterActivity.this, "Password inconsistent!", Toast.LENGTH_LONG).show();
                   return;
               }
               if (TextUtils.isEmpty(phone)) {
                   Toast.makeText(RegisterActivity.this, "Cannot be null", Toast.LENGTH_LONG).show();
                   return;
               }




       }

   }
    /*@Override
        public void onClick(View view){
        String idnum = idn.getText().toString().trim();
        String name = namee.getText().toString();
        String password = psword.getText().toString().trim();
        String passConfirm = pswordc.getText().toString();
        String phone = tel.getText().toString();
            if (TextUtils.isEmpty(idnum)) {
                Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
                return;
            }
            databaseHelper = new DatabaseHelper(this);
//        查询这个账号是否已经被注册
            List<User> userList = databaseHelper.selectByUname(idnum);
            if (!userList.isEmpty()) {  //说明已经被注册过了
                Toast.makeText(RegisterActivity.this, "Already registered", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(RegisterActivity.this, "Cannot be null!", Toast.LENGTH_LONG).show();
                return;
            }
            if (!TextUtils.equals(password, passConfirm)) {
                Toast.makeText(RegisterActivity.this, "Password inconsistent!", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(RegisterActivity.this, "Cannot be null", Toast.LENGTH_LONG).show();
                return;
            }


            User user = new User();
            user.setIdnum(idnum);
            user.setName(name);
            user.setPassword(password);
            user.setTel(phone);

            //插入数据到数据库中
            databaseHelper = new DatabaseHelper(this);
            long rowId = databaseHelper.Register(user);
            if (rowId != -1) {
                Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Register Failed!", Toast.LENGTH_LONG).show();
            }


        }*/
    }



