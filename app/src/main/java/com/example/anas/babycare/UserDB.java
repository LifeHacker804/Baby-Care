package com.example.anas.babycare;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends SQLiteOpenHelper {

    public UserDB(Context context) {
        super(context, "data", null, 1);
    }

    // Creating Tables
    @Override     public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table user(name text,email text,pass text,phone text,username text )");
        db.execSQL("create table child(name text,bday text,height text,weight text,gender text,pname text)");

        db.execSQL("create table food(fname text,date text,time text,quantity text,cname text)");
        db.execSQL("create table heightweight(height text,weight text,cname text)");
        db.execSQL("create table sleep(starttime text,endtime text,date text,cname text)");
        db.execSQL("create table vaccination(vacname text,date text,cname text)");
        db.execSQL("create table notes(title text,description text,date text,cname text)");


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {         // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS child");


        // Create tables again
        onCreate(db);
    }







}