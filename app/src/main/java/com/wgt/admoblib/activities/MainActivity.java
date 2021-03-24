package com.wgt.admoblib.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wgt.admoblib.R;
import com.wgt.admoblib.ads.Callback;
import com.wgt.admoblib.ads.MyAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{ setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); } catch(Exception ignore){}
        setContentView(R.layout.activity_main);

        MyAds.initBannerIds(this);
        MyAds.initInterAds(this);

    }

    public void ShowAds(View view) {
        MyAds.showInterFull(this, new Callback() {
            @Override
            public void callBack(Object value, int where) {
                Toast.makeText(MainActivity.this, "Chuyển màn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}