package com.example.semesterproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlite_katmani extends SQLiteOpenHelper {

    public sqlite_katmani(Context c){
        super(c,"kullanici",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table notlar(baslik text,note text,tarih text,oncelik integer,renk integer, galeri_path text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists notlar");
    }
}
