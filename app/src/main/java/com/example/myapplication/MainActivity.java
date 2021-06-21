package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<item> list = new ArrayList();
//    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("대전광역시 코로나 정보 알리미");

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        new Description().execute();
//
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(intent);
//
//                return false;
//            }
//        });

    }

    private class Description extends AsyncTask<Void, Void, Void> {

//
//        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//
//
//            progressDialog = new ProgressDialog(MainActivity.this);
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setMessage("Wait...");
//            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect("https://corona.daejeon.go.kr/index.do?menuId=0008").get();
                Elements mElementDataSize = doc.select("table[class=corona]").select("tr");
//                int mElementSize = mElementDataSize.size();
//                Log.e("크기", ""+mElementSize);
//                intent.putExtra("kk", mElementSize);

                for(Element elem : mElementDataSize) {

                    String district = elem.select("tr td p").eq(0).text();
                    String facility = elem.select("tr td p").eq(1).text();
                    //String name = elem.select("tr td p").eq(2).text();
                    String name = elem.select("tr td:nth-child(3)  p").text();
                    String address = elem.select("tr td:nth-child(4)  p").text();
                    String visitTime = elem.select("tr td:nth-child(5)  p:nth-child(1)").text();

                    String visitTime2 = elem.select("tr td:nth-child(5)  p").text();
                    String disinfection = elem.select("tr td:nth-child(6)  p").text();

                    if (name == ""){
                        System.out.println("1111111111111111");
                    }
                    else {
                    list.add(new item(district, facility, name, address, visitTime, visitTime2, disinfection));}

//                    while (list.remove(String.valueOf(exceptions))){};

                }



                Log.d("debug :", "List " + mElementDataSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPostExecute(Void result) {

            MyAdapter myAdapter = new MyAdapter(getApplicationContext(),list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);

//            progressDialog.dismiss();
        }
    }

    public void btn1(View view){
        Intent intent = new Intent(this, ReadMore.class);
        startActivity(intent);
    }

}