package com.example.myapplication.activity;

public class User {//id idnum name passsword tel
    private  int id;
    private  String idnum;

    private  String name;
    private  String password;

    private String tel;
    public User(){}
    public User(int id, String idnum, String name, String password, String tel){
        super();
        this.id = id;
        this.name = name;
        this.idnum = idnum;
        this.password = password;
        this.tel = tel;
    }
    public  int getId() {
        return  this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIdnum() {
        return this.idnum;
    }
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public  String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }




}
