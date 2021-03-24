package com.wgt.admoblib.ads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.wgt.admoblib.R;

public class MyAds {

    public static boolean isSplashRunning = false;

    public static void clearInterAds(){
        MyAdmobAds.clearInter();
    }

    public static void initInterAds(Context context){

        if(!AdsConfig.isShowInter()){
            return;
        }
        if(AdsConfig.isFacebookAds()){
            return;
        }

        MyAdmobAds.initInterId(context);
    }

    public static boolean isInterLoaded(){
        if(AdsConfig.isFacebookAds()){
            return true;
        }
        return MyAdmobAds.isInterLoaded();
    }

    public static void initBannerIds(Activity context){

        if(!AdsConfig.isShowBanner()|| AdsConfig.isFacebookAds()){
            context.findViewById(R.id.tv_banner_loading).setVisibility(View.GONE);
            return;
        }
        MyAdmobAds.initBannerAds(context);
    }

    public static void showInterFull(Context context, Callback callback){

        if(!AdsConfig.isShowInter()){
            callback.callBack(0,0);
            return;
        }

        if(AdsConfig.isFacebookAds()){
            callback.callBack(0,0);
            return;
        }

        if(MyAds.isInterLoaded()&& MyAdmobAds.flagQC==1){
            Dialog dialog = new Dialog(context, R.style.Translucent);
            dialog.setContentView(R.layout.dialog_ad_break);
            dialog.setCancelable(false);
            dialog.show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if(dialog.isShowing())dialog.dismiss();
                MyAdmobAds.showAdsFull(callback);
            },2000);
        }else {
            callback.callBack(0,0);
        }

    }



    public static void showInterFullNow(Context context, Callback callback){

        if(!AdsConfig.isShowInter()){
            callback.callBack(0,0);
            return;
        }

        if(AdsConfig.isFacebookAds()){
            callback.callBack(0,0);
            return;
        }

        if(MyAds.isInterLoaded()){
            Dialog dialog = new Dialog(context, R.style.Translucent);
            dialog.setContentView(R.layout.dialog_ad_break);
            dialog.setCancelable(false);
            dialog.show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if(dialog.isShowing())dialog.dismiss();
                if(isSplashRunning){
                    MyAdmobAds.showAdsFullNow(callback);
                }else {
                    callback.callBack(0,0);
                }
            },2000);
        }else {
            callback.callBack(0,0);
        }

    }
}
