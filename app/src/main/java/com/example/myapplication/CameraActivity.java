package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    CameraSurfaceView surfaceView;
    Bitmap bitmap;

    ImageButton btnShoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "카메라부분 사용을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(this, "카메라부분 사용을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();

            }
        }

        surfaceView = findViewById(R.id.surfaceview);

        btnShoot = (ImageButton) findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnShoot.setEnabled(false);
                Toast.makeText(getApplicationContext(),"잠시만 기다려주세요.",Toast.LENGTH_SHORT).show();
                capture();
                btnShoot.setEnabled(true);
            }
        });

    }

    public void capture(){
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                camera.startPreview();
                bitmap =  Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//                bitmap = bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/15,bitmap.getHeight()/5,true);

                //ret = comparePoketmon(bitmap);
                //System.out.println(ret);
                //if(ret==898){
                //    Toast.makeText(getApplicationContext(),"확인 후 다시 시도해주시기 바랍니다.",Toast.LENGTH_SHORT).show();
                //}
                //else{
                //    showDialog();
                //}
            }
        });
    }

}
