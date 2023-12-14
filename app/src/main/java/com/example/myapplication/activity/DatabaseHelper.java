package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.Department_doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Hospital";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER = "create table user(id integer primary key autoincrement," +
            "idnum text," +
            "password text," +
            "tel text)";

    private static final String CREATE_DEPARTMENT_DOCTOR = "create table department_doctor" +
            "(id integer primary key autoincrement, department text, doctor text)";

    private static final String CREATE_USER_RESERVATION = "create table user_reservation" +
            "(id integer primary key autoincrement, user_id_num text, username text,date text, time_slot text," +
            "department text, doctor text)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_DEPARTMENT_DOCTOR);
        db.execSQL(CREATE_USER_RESERVATION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addDepartmentDoctor(Department_doctor dd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("department", dd.getDepartment());
        values.put("doctor", dd.getDoctor());
        db.insert("department_doctor", null, values);
        db.close();
    }

    public Department_doctor getDepartmentDoctor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("department_doctor", new String[]{"id", "department", "doctor"},
                "id=?", new String[]{String.valueOf(id)}, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Department_doctor dd = new Department_doctor(Integer.parseInt(cursor.getString(0))
                , cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return dd;
    }

    public int delDepartmentDoctor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        int deletedRow = db.delete("department_doctor", whereClause, whereArgs);
        db.close();
        return deletedRow;

    }

    public long Register(User u){//
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idnum", u.getIdnum());
        values.put("name", u.getName());
        values.put("password", u.getPassword());
        values.put("tel", u.getTel());
        db.close();
        return db.insert(CREATE_USER,null,values);
    }

    public List<User> selectByUnameAndPsword(String idnum, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CREATE_USER, null, "idnum=? and pass=?", new String[]{idnum, password}, null, null, null);
        List<User> userList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String idnum1 = cursor.getString(cursor.getColumnIndex("idnum"));
                @SuppressLint("Range") String password1 = cursor.getString(cursor.getColumnIndex("password"));
                User user = new User();
                user.setIdnum(idnum1);
                user.setPassword(password1);
                userList.add(user);
            }
            return userList;
        }
        return null;
    }

    public List<User> selectByUname(String idnum) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CREATE_USER, null, "account=?", new String[]{idnum}, null, null, null);
        List<User> userList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String idnum1 = cursor.getString(cursor.getColumnIndex("idnum"));
                User user = new User();
                user.setIdnum(idnum1);
                userList.add(user);
            }
            return userList;
        }
        return null;
    }


    public long addUserReservation(User_Reservation ur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("user_id_num", ur.getUser_id_num());
        values.put("username", ur.getUsername());
        values.put("date", ur.getDate());
        values.put("time_slot", ur.getTime_slot());
        values.put("department", ur.getDepartment());
        values.put("doctor", ur.getDoctor());
        long userReservation = db.insert("user_reservation", null, values);

        db.close();
        return userReservation;
    }

    public User_Reservation getUserReservation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user_reservation", new String[]{"id", "user_id_num",
                        "username","date","time_slot","department","doctor"},
                "id=?", new String[]{String.valueOf(id)}, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        User_Reservation ur = new User_Reservation(Integer.parseInt(cursor.getString(0))
                , cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        cursor.close();
        db.close();
        return ur;
    }

    public List<User_Reservation> getAllUserReservation() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User_Reservation> allReservation = new ArrayList<>();
        label1 :
        {
            for (int i = 1; i < 100; i++) {  //一次获取一百条
                try {
                    Cursor cursor = db.query("user_reservation", new String[]{"id", "user_id_num",
                                    "username", "date", "time_slot", "department", "doctor"},
                            "id=?", new String[]{String.valueOf(i)}, null, null,
                            null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                    }
                    User_Reservation ur = new User_Reservation(Integer.parseInt(cursor.getString(0))
                            , cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                    if (ur!=null)
                        allReservation.add(ur);
                    cursor.close();
                } catch (Exception ignored) {}

            }
        }
        db.close();
        return allReservation;
    }

    public int delUserReservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        int deletedRow = db.delete("user_reservation", whereClause, whereArgs);
        db.close();
        return deletedRow;

    }



    public List<String> findDoctorsByDepartment(String department) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT doctor FROM department_doctor WHERE department = ?", new String[] {department});
        Cursor cursor = db.query("department_doctor", new String[]{"id","department","doctor"},
                "department=?", new String[]{String.valueOf(department)}, null, null,
                null, null);
        List<String> doctors = new ArrayList<>();
        while (cursor.moveToNext()) {
            doctors.add(cursor.getString(2));
        }
        cursor.close();
        return doctors;
    }





    //注册方法实现
    public long RegisterActivity(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", u.getId());
        cv.put("username ", u.getIdnum());
        cv.put("password", u.getPassword());
        cv.put("phone", u.getTel());
        long users = db.insert("user", null, cv);
        return users;
    }

    //登录方法实现
    public boolean LoginActivity(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;
        Cursor users = db.query("user", null, "username like?", new String[]{username}, null, null, null);
        if (users != null) {
            while (users.moveToNext()) {
                @SuppressLint("Range") String username1 = users.getString(users.getColumnIndex("username"));
                Log.i("users", "login: " + username1);
                String password1 = users.getString(2);
                Log.i("users", "login: " + password1);
                result = password1.equals(password);
                return result;
            }
        }
        return false;
    }

    //根据idnum和telno查找当前登录用户信息
    public User select(String username) {
        SQLiteDatabase db = getWritableDatabase();
        User SelectUser = new User();
        Cursor user = db.query("user", new String[]{"username", "phone"}, "username=?", new String[]{username}, null, null, null);
        while (user.moveToNext()) {
            @SuppressLint("Range") String uname = user.getString(user.getColumnIndex("username"));
            @SuppressLint("Range") String phone = user.getString(user.getColumnIndex("phone"));
            SelectUser.setIdnum(uname);
            SelectUser.setTel(phone);
        }
        user.close();
        return SelectUser;
    }


}

