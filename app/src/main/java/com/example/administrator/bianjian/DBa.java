package com.example.administrator.bianjian;

import org.litepal.crud.DataSupport;

public class DBa extends DataSupport {
    private String content;
    private String Date;
    private int num;
    private int Id;



    public DBa() {
    }

    public DBa(String content, String date) {
        this.content = content;
        Date = date;
    }

    public DBa(String content, String date, int num) {
        this.content = content;
        Date = date;
        this.num = num;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
