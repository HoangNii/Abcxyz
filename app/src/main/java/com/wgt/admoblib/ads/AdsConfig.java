package com.wgt.admoblib.ads;

import com.wgt.admoblib.BuildConfig;

public class AdsConfig {

    private static final String BANNER_ID_TEST = "ca-app-pub-3940256099942544/6300978111";

    private static final String INTER_ID_TEST = "ca-app-pub-3940256099942544/1033173712";

    private static final String NATIVE_ID_TEST = "ca-app-pub-3940256099942544/2247696110";


    public static boolean isFacebookAds(){
        return AdsLoaded.get().getInAppPlatForm().contains("facebook");
    }

    public static boolean isSFacebookAds(){
        return AdsLoaded.get().getSystemPlatForm().contains("facebook");
    }

    public static boolean isShowBanner(){
        return AdsLoaded.get().getInShowBanner().equals("1");
    }

    public static boolean isShowSBanner(){
        return AdsLoaded.get().getSystemShowBanner().equals("1");
    }

    public static boolean isShowInter(){
        return AdsLoaded.get().getInShowInter().equals("1");
    }

    public static boolean isShowSInter(){
        return AdsLoaded.get().getSystemShowInter().equals("1");
    }

    public static boolean isShowNative(){
        return AdsLoaded.get().getInShowNative().equals("1");
    }

    public static boolean isShowSNative(){
        return AdsLoaded.get().getSystemShowNative().equals("1");
    }

    public static String getBannerId(){
        return BuildConfig.DEBUG?BANNER_ID_TEST: AdsLoaded.get().getInAppBannerId();
    }

    public static String getSBannerId(){
        return BuildConfig.DEBUG?BANNER_ID_TEST: AdsLoaded.get().getSystemAppBannerId();
    }

    public static String getInterId(){
        return BuildConfig.DEBUG?INTER_ID_TEST: AdsLoaded.get().getInAppInterId();
    }

    public static String getSInterId(){
        return BuildConfig.DEBUG?INTER_ID_TEST: AdsLoaded.get().getSystemAppInterId();
    }

    public static String getNativeId(){
        return BuildConfig.DEBUG?NATIVE_ID_TEST: AdsLoaded.get().getInAppNativeId();
    }

    public static String getSNativeId(){
        return BuildConfig.DEBUG?NATIVE_ID_TEST: AdsLoaded.get().getSystemAppNativeId();
    }

    public static int getTimeDelay(){
        return BuildConfig.DEBUG ?5000: Integer.parseInt(AdsLoaded.get().getInterDelay())*1000;
    }
}
