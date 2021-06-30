package com.podata.projectD;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
//implements SensorEventListener

    private RecyclerView recyclerView;
    private final ArrayList<item> list = new ArrayList<>();
    final Bundle bundle = new Bundle();
    TextView yesterday_num_text;

    SensorManager mSensorManager;
    Sensor mAccelerometer;
    private long mShakeTime;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private final int MY_PERMISSIONS_REQUEST_CAMERA=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("대전광역시 코로나 정보 알리미");

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        yesterday_num_text = (TextView) findViewById(R.id.yesterday_num);


        new Description().execute();
    }


//쉐이크 기능
//    protected void onResume(){
//        super.onResume();
//        mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
//    }
//    protected void onPause(){
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
//            float axisX = event.values[0];
//            float axisY = event.values[1];
//            float axisZ = event.values[2];
//
//            float gravityX = axisX / SensorManager.GRAVITY_EARTH;
//            float gravityY = axisY / SensorManager.GRAVITY_EARTH;
//            float gravityZ = axisZ / SensorManager.GRAVITY_EARTH;
//
//            Float f = gravityX * gravityX + gravityY * gravityY + gravityZ * gravityZ;
//            double squaredD = Math.sqrt(f.doubleValue());
//            float gForce = (float)squaredD;
//            if(gForce > SHAKE_THRESHOLD_GRAVITY){
//                long currentTime = System.currentTimeMillis();
//                if (mShakeTime + SHAKE_SKIP_TIME > currentTime){
//                    return;
//                }
//                mShakeTime = currentTime;
//
//                int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//                if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                            Manifest.permission.CAMERA)) {
//                        Toast.makeText(this,"카메라부분 사용을 위해 카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();
//                    } else {
//                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//                        Toast.makeText(this,"카메라부분 사용을 위해 카메라 권한이 필요합니다.",Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Intent intent = new Intent(this, CameraActivty.class);
//                    startActivity(intent);
//                }
//            }
//
//        }
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }



    private class Description extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect("https://corona.daejeon.go.kr/index.do?menuId=0008").get();
                Elements mElementDataSize = doc.select("table[class=corona]").select("tr");

                for(Element elem : mElementDataSize) {

                    String district = elem.select("tr td p").eq(0).text();
                    String facility = elem.select("tr td p").eq(1).text();
                    //String name = elem.select("tr td p").eq(2).text();
                    String name = elem.select("tr td:nth-child(3)  p").text();
                    String address = elem.select("tr td:nth-child(4)  p").text();
                    String visitTime = elem.select("tr td:nth-child(5)  p:nth-child(1)").text();

                    String visitTime2 = elem.select("tr td:nth-child(5)  p").text();
                    String disinfection = elem.select("tr td:nth-child(6)  p").text();



                    if(!name.equals("") && !name.contains("역학조사"))
                        list.add(new item(district, facility, name, address, visitTime, visitTime2, disinfection));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Document doc = Jsoup.connect("https://corona.daejeon.go.kr/index.do").get();
                String text5 = doc.select("div [class=contents dj_list] tr:nth-child(2) td:nth-child(2) strong").text();

                bundle.putString("text5", text5);

                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);

            }catch (IOException e){
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
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();

            yesterday_num_text.setText(bundle.getString("text5"));
            return false;
        }
    });

    public void readMoreClick(View view){
        Intent intent = new Intent(this, ReadMore.class);
        startActivity(intent);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "승인이 허가되어 있습니다.", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "아직 승인받지 않았습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}