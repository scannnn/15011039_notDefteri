package com.example.semesterproject;

import java.util.ArrayList;
import java.util.Date;

public class not {
    private String baslik;
    private String tarih;
    private String not;
    private String adres;   //Google maps' ten çek
    private int renk;
    private int oncelik;
    private String galeri_path;

    public not(String baslik, String not, String tarih,int oncelik,int renk, String galeri_path) {
        this.baslik = baslik;
        this.not = not;
        this.tarih = tarih;
        this.oncelik = oncelik;
        this.renk = renk;
        this.galeri_path = galeri_path;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public int getRenk() {
        return renk;
    }

    public void setRenk(int renk) {
        this.renk = renk;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getGaleri_path() {
        return galeri_path;
    }

    public void setGaleri_path(String  galeri_path) {
        this.galeri_path = galeri_path;
    }

    public int getOncelik() {
        return oncelik;
    }

    public void setOncelik(int priority) {
        this.oncelik = oncelik;
    }
    }

