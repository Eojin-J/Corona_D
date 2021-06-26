package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ReadMore extends AppCompatActivity {
    private ArrayList<MoreItem> morelist = new ArrayList();
    TextView total_num, release_num, isolate_num, rip_num, total_before_num, release_before_num, isolate_before_num, rip_before_num;
    TextView baseDate;
    ImageButton callHanbat, callGwangeo, callExpo, callDong, callJung, callWestern, callYoosung, callDaedeok;
    final Bundle bundle = new Bundle();
    String Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmore);

        total_num = (TextView) findViewById(R.id.total_num);
        release_num = (TextView) findViewById(R.id.release_num);
        isolate_num = (TextView) findViewById(R.id.isolate_num);
        rip_num = (TextView) findViewById(R.id.rip_num);

        total_before_num = (TextView) findViewById(R.id.total_before_num);
        release_before_num = (TextView) findViewById(R.id.release_before_num);
        isolate_before_num = (TextView) findViewById(R.id.isolate_before_num);
        rip_before_num = (TextView) findViewById(R.id.rip_before_num);
        baseDate = (TextView) findViewById(R.id.baseDate);

//        callHanbat = (ImageButton)  findViewById(R.id.callHanbat);
//        callGwangeo = (ImageButton)  findViewById(R.id.callGwangeo);
//        callExpo = (ImageButton)  findViewById(R.id.callExpo);
//        callDong = (ImageButton)  findViewById(R.id.callDong);
//        callJung = (ImageButton)  findViewById(R.id.callJung);
//        callWestern = (ImageButton)  findViewById(R.id.callWestern);
//        callYoosung = (ImageButton)  findViewById(R.id.callYoosung);
//        callDaedeok = (ImageButton)  findViewById(R.id.callDaedeok);
//
//        callHanbat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Korean_Tuberculosis_Association);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callGwangeo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Korean_Tuberculosis_Association);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callExpo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Korean_Tuberculosis_Association);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callDong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Dong_gu_Health_Center);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callJung.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Jung_gu_Health_Center);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callWestern.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Western_Health_Center);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callYoosung.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Yuseong_Health_Center);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });
//
//        callDaedeok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Num = getString(R.string.Daedeok_gu_Health_Center);
//                Num = "tel:" + Num;
//                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(Num)));
//            }
//        });

        new Thread(){
            @Override
            public void run(){
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://corona.daejeon.go.kr/index.do").get();
                    String baseDate = doc.select("div [class=contents] h3:nth-child(1) span:nth-child(2)").text();
                    String total = doc.select("div [class=contents dj_list] tr:nth-child(1) td:nth-child(2) strong").text();
                    String text2 = doc.select("div [class=contents dj_list] tr:nth-child(1) td:nth-child(3) strong").text();
                    String text3 = doc.select("div [class=contents dj_list] tr:nth-child(1) td:nth-child(4) strong").text();
                    String text4 = doc.select("div [class=contents dj_list] tr:nth-child(1) td:nth-child(5) strong").text();

                    String text5 = doc.select("div [class=contents dj_list] tr:nth-child(2) td:nth-child(2) strong").text();
                    String text6 = doc.select("div [class=contents dj_list] tr:nth-child(2) td:nth-child(3) strong").text();
                    String text7 = doc.select("div [class=contents dj_list] tr:nth-child(2) td:nth-child(4) strong").text();
                    String text8 = doc.select("div [class=contents dj_list] tr:nth-child(2) td:nth-child(5) strong").text();

                    bundle.putString("base", baseDate);
                    bundle.putString("total", total);
                    bundle.putString("text2", text2);
                    bundle.putString("text3", text3);
                    bundle.putString("text4", text4);

                    bundle.putString("text5", text5);
                    bundle.putString("text6", text6);
                    bundle.putString("text7", text7);
                    bundle.putString("text8", text8);

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();


    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Bundle bundle = msg.getData();
            baseDate.setText(bundle.getString("base"));

            total_num.setText(bundle.getString("total"));
            release_num.setText(bundle.getString("text2"));
            isolate_num.setText(bundle.getString("text3"));
            rip_num.setText(bundle.getString("text4"));

            total_before_num.setText(bundle.getString("text5"));
            release_before_num.setText(bundle.getString("text6"));
            isolate_before_num.setText(bundle.getString("text7"));
            rip_before_num.setText(bundle.getString("text8"));

        }
    };

    public void hospitalBtn(View view){
        Intent intent = new Intent(this, HospitalActivity.class);
        startActivity(intent);
    }

    public void backBtn(View view){
        finish();
    }


}