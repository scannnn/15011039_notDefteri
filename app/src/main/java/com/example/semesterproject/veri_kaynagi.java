package com.example.semesterproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class veri_kaynagi {
    SQLiteDatabase db;
    sqlite_katmani bdb;
    public veri_kaynagi(Context c){
        bdb = new sqlite_katmani(c);
    }

    public void ac(){
        db = bdb.getWritableDatabase();
    }

    public void kapat(){
        bdb.close();
    }

    public void not_kaydet(not kaydedilecek_not){
        ContentValues val = new ContentValues();
        val.put("baslik",kaydedilecek_not.getBaslik());
        val.put("note",kaydedilecek_not.getNot());
        val.put("tarih",kaydedilecek_not.getTarih());
        val.put("oncelik",kaydedilecek_not.getOncelik());
        val.put("renk",kaydedilecek_not.getRenk());
        val.put("galeri_path",kaydedilecek_not.getGaleri_path());
        db.insert("notlar",null,val);
    }

    public void not_sil(not silinecek_not){
        db.delete("notlar","tarih = ?",new String[]{silinecek_not.getTarih()});
    }

    public void guncelle(not guncellenecek_not){
        ContentValues val = new ContentValues();
        val.put("baslik",guncellenecek_not.getBaslik());
        val.put("note",guncellenecek_not.getNot());
        val.put("tarih",guncellenecek_not.getTarih());
        val.put("oncelik",guncellenecek_not.getOncelik());
        val.put("renk",guncellenecek_not.getRenk());
        val.put("galeri_path",guncellenecek_not.getGaleri_path());
        db.update("notlar",val,"tarih = ?",new String[]{guncellenecek_not.getTarih()});
    }

    public List<not> listele(){
        List<not> liste = new ArrayList<>();
        String kolonlar [] = {"baslik","note","tarih","oncelik","renk","galeri_path"};
        Cursor c = db.query("notlar",kolonlar,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            String baslik = c.getString(0);
            String not = c.getString(1);
            String tarih = c.getString(2);
            int oncelik = c.getInt(3);
            int renk = c.getInt(4);
            String galeri_path = c.getString(5);
            not temp = new not(baslik, not, tarih, oncelik, renk, galeri_path);
            liste.add(temp);
            c.moveToNext();
        }
        return liste;
    }
}
