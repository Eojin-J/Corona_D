package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HospitalActivity extends AppCompatActivity {

    LinearLayout callHanbat, callGwangeo, callExpo, callDong, callJung, callWestern, callYoosung, callDaedeok;
    String Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        callHanbat = (LinearLayout)  findViewById(R.id.hospital1);
        callGwangeo = (LinearLayout)  findViewById(R.id.hospital2);
        callExpo = (LinearLayout)  findViewById(R.id.hospital3);
        callDong = (LinearLayout)  findViewById(R.id.hospital4);
        callJung = (LinearLayout)  findViewById(R.id.hospital5);
        callWestern = (LinearLayout)  findViewById(R.id.hospital6);
        callYoosung = (LinearLayout)  findViewById(R.id.hospital7);
        callDaedeok = (LinearLayout)  findViewById(R.id.hospital8);

        callHanbat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Korean_Tuberculosis_Association);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callHanbat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Korean_Tuberculosis_Association);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callGwangeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Korean_Tuberculosis_Association);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Korean_Tuberculosis_Association);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Dong_gu_Health_Center);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callJung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Jung_gu_Health_Center);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callWestern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Western_Health_Center);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callYoosung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Yuseong_Health_Center);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

        callDaedeok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Num = getString(R.string.Daedeok_gu_Health_Center);
                Num = "tel:" + Num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
            }
        });

    }

    public void backBtn(View view){
        finish();
    }

}

