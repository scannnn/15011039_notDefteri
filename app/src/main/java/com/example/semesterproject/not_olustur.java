package com.example.semesterproject;

import android.Manifest;
import android.app.FragmentManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import yuku.ambilwarna.AmbilWarnaDialog;

public class not_olustur extends AppCompatActivity {
    private static final int PICK_FROM_GALLERY = 100;
    EditText baslik;
    EditText not;
    CheckBox checkBox;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    int renk;
    ArrayList<String> galeri_path = new ArrayList<>();
    ArrayList<ImageView> images = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_olustur);
        this.getSupportActionBar().hide();

        baslik = findViewById(R.id.baslik2);
        not = findViewById(R.id.not);
        checkBox = findViewById(R.id.checkBox);
        renk = ContextCompat.getColor(this,R.color.colorPrimary);  // default rengi alma
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);
        image3.setVisibility(View.INVISIBLE);
        image4.setVisibility(View.INVISIBLE);
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setVisibility(View.INVISIBLE);
                image1.setImageDrawable(null);
                galeri_path.remove(0);
                fotograflari_ekle();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image2.setVisibility(View.INVISIBLE);
                image2.setImageDrawable(null);
                galeri_path.remove(1);
                fotograflari_ekle();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image3.setVisibility(View.INVISIBLE);
                image3.setImageDrawable(null);
                galeri_path.remove(2);
                fotograflari_ekle();
            }
        });

    }

    public void fotograflari_ekle(){
        int i =0;
        File imgFile;
        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);
        image3.setVisibility(View.INVISIBLE);
        image4.setVisibility(View.INVISIBLE);
        while(i<galeri_path.size() && i<3){
            imgFile = new File(galeri_path.get(i));
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                images.get(i).setImageBitmap(myBitmap);
                images.get(i).setVisibility(View.VISIBLE);
            }
            i++;
        }
        if (i == 3) images.get(i).setVisibility(View.VISIBLE);
        else images.get(i).setVisibility(View.INVISIBLE);
    }

    public void notu_kaydet(View view) {
        String baslikk = baslik.getText().toString();
        String nott = not.getText().toString();
        int oncelik;
        Date date = Calendar.getInstance().getTime();
        String tarih = date.toString();

        if(checkBox.isChecked()) oncelik = 1;
        else oncelik = 0;


        veri_kaynagi vk = new veri_kaynagi(this);
        vk.ac();
        vk.not_kaydet(new not(baslikk,nott, tarih, oncelik, renk, ortak_fonksiyonlar.convertArrayToString(galeri_path)));
        vk.kapat();
        Toast.makeText(this,"Notunuz başarıyla kaydedilmiştir.",Toast.LENGTH_SHORT).show();
        this.finish();
}

    public void renkSec(View view) {
        openColorPicker();
    }

    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, renk, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                renk = color;
                not.setBackgroundColor(renk);
                baslik.setBackgroundColor(renk);
            }
        });
        colorPicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri galeri_nesnesi;
        if (resultCode == RESULT_OK && requestCode == PICK_FROM_GALLERY){
            galeri_nesnesi = data.getData();
            galeri_path.add(ortak_fonksiyonlar.getPathFromUri(this,galeri_nesnesi));
            fotograflari_ekle();
        }
    }

    public void galeriden_sec(View view){
        Intent mediaChooser = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mediaChooser.setType("image/*,video/*");
        startActivityForResult(mediaChooser, PICK_FROM_GALLERY);
    }


    public void check_on_click(View view) {
        if (checkBox.isChecked())
            Toast.makeText(this,"Notunuza öncelik atanmıştır.",Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,"Notunuzun önceliği alınmıştır.",Toast.LENGTH_SHORT).show();
    }
}
