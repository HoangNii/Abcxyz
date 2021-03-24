package com.wgt.admoblib.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.wgt.admoblib.R;
import com.wgt.admoblib.ads.AdsLoader;
import com.wgt.admoblib.ads.MyAds;

public class SplashActivity extends AppCompatActivity {

    private int delay = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{ setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); } catch(Exception ignore){}
        setContentView(R.layout.activity_splash);

        MyAds.isSplashRunning = true;

        AdsLoader.get().synWithCallback((value, where) -> {

            if(!MyAds.isInterLoaded()){
                MyAds.initInterAds(SplashActivity.this);
                delay = 4000;
            }

            new Handler().postDelayed(() -> {
                if(MyAds.isSplashRunning){
                    MyAds.showInterFullNow(SplashActivity.this, (value1, where1) -> {
                        goMain();
                    });
                }else {
                    goMain();
                }

            },delay);

        });
    }

    private void goMain() {
        startActivity(new Intent(SplashActivity.this,MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyAds.isSplashRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyAds.isSplashRunning = false;
    }
}