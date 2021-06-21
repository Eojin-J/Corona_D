package com.example.myapplication;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView textView, textView_ad, textView_vs;
    Button whereBtn;
    private static final int REQUEST_CODE = 0;
    private GoogleMap mMap;
    private Geocoder geocoder;
    String visitTime2;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView_name);
        textView_ad = findViewById(R.id.textView_address);
        textView_vs = findViewById(R.id.textView_visitTime);
        whereBtn = findViewById(R.id.whereBtn);


        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String visitTime = getIntent().getStringExtra("visitTime");

//        Log.e("ㅈㅇㅇㅇㅇㅇ", intent.getStringExtra("visitTime"));
//            i = intent.getIntExtra("kk", 0);
//            //visitTime2 = intent.getStringExtra("visitTime2");
//        Log.e("록으", ""+i);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView.setText(name);
        textView_ad.setText(address);
        textView_vs.setText(visitTime);

        whereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = textView_ad.getText().toString();
                List<Address> addressList = null;
                try{
                    addressList = geocoder.getFromLocationName(str, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());

                String []splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2);
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1);
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1);
                System.out.println(latitude);
                System.out.println(longitude);

                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("search result");
                mOptions2.snippet(address);
                mOptions2.position(point);
                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,17));
            }
        });

//        try {
//            list_ad = geocoder.getFromLocationName(str, 1);
//            double mLat = list_ad.get(0).getLatitude();
//            double mLng = list_ad.get(0).getLongitude();
//            Log.d("Eojin", "위도"+mLat+"경도"+mLng);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
//        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);

        // Add a marker in Sydney and move the camera
        LatLng hanbat = new LatLng(36.350806, 127.300621);
        mMap.addMarker(new MarkerOptions()
                .position(hanbat)
                .title("Marker in Hanbat"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanbat, 16));
    }

//
//    public void setLocation(String address){
//
//        String str = address.toString();
//
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+str);
//
//        Geocoder geocoder;
//        List<Address> list_ad = null;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try{
//            list_ad = geocoder.getFromLocationName(str,1);
//            double mLat = list_ad.get(0).getLatitude();
//            double mLng = list_ad.get(0).getLongitude();
//
//            System.out.println("sdasdasdsdasdasdadsadsadsadsdsa"+ mLat + mLng);
//
//            LatLng loc = new LatLng(mLat, mLng);
//            mMap.addMarker(new MarkerOptions().position(loc).title(""));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
