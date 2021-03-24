package com.wgt.admoblib;

import android.util.Log;

import com.wgt.admoblib.BuildConfig;

public class DebugLog {

    public static void e(String key,String vl){
        if(BuildConfig.DEBUG){
            Log.e(key,vl);
        }
    }
    public static void d(String key,String vl){
        if(BuildConfig.DEBUG){
            Log.d(key,vl);
        }
    }
    public static void i(String key,String vl){
        if(BuildConfig.DEBUG){
            Log.i(key,vl);
        }
    }
}
