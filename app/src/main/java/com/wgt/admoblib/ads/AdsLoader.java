package com.wgt.admoblib.ads;

import com.flurry.android.FlurryConfig;
import com.flurry.android.FlurryConfigListener;
import com.wgt.admoblib.BuildConfig;
import com.wgt.admoblib.DebugLog;

public class AdsLoader {

    private static final String TAG = AdsLoader.class.getSimpleName();

    private Callback callback;

    private static AdsLoader adsLoader;

    public static AdsLoader get(){
        if(adsLoader ==null){
            adsLoader = new AdsLoader();
        }
        return adsLoader;
    }

    public void synWithCallback(Callback callback) {
        this.callback = callback;
        syn();
    }

    private void syn(){
        final FlurryConfig mFlurryConfig = FlurryConfig.getInstance();
        mFlurryConfig.registerListener(new FlurryConfigListener() {
            @Override
            public void onFetchSuccess() {
                mFlurryConfig.activateConfig();
            }

            @Override
            public void onFetchNoChange() {
                if(callback!=null){
                    callback.callBack(0,0);
                    callback = null;
                }
                DebugLog.d(TAG, "onFetchNoChange");
                mFlurryConfig.unregisterListener(this);
            }

            @Override
            public void onFetchError(boolean b) {
                if(callback!=null){
                    callback.callBack(0,0);
                    callback = null;
                }
                DebugLog.d(TAG, "onFetchError");
                mFlurryConfig.unregisterListener(this);
            }

            @Override
            public void onActivateComplete(boolean b) {

                String update_link = mFlurryConfig.getString("update_link","Update");
                String update_message = mFlurryConfig.getString("update_message","Update");
                String update_action_title = mFlurryConfig.getString("update_action_title","Update");
                String update_mode =  mFlurryConfig.getString("update_mode","Update");
                String update_title = mFlurryConfig.getString("update_title","Update");
                String update_icon = mFlurryConfig.getString("update_icon","Update");
                String update_is_ad = mFlurryConfig.getString("update_is_ad","1");
                String target_id = mFlurryConfig.getString("target_id","Update");
                String ads_frequency = mFlurryConfig.getString("ads_frequency","70");
                String ads_limit = mFlurryConfig.getString("ads_limit","10");
                String app_version_code = mFlurryConfig.getString("app_version_code","0");

                //test Update
//                String update_link = "https://play.google.com/store/apps/details?id=com.crusader.flashlight";
//                String update_message = "Click To Update Flashlight App";
//                String update_action_title = "Download";
//                String update_mode =  "1";
//                String update_title = "Flashlight";
//                String update_icon = "https://lh3.googleusercontent.com/A6Brba_As8CsvxvZ2wmYAtHanhApv07m3_96FIUVwFMSmw4IDoWMCfVJjML-qF7IIo8=s180";
//                String update_is_ad = "0";
//                String target_id = "com.crusader.flashlight";
//                String ads_frequency = "70";
//                String ads_limit = "10";
//                String app_version_code = "6";


                AdsLoaded.get().setUpdate(
                        update_link,
                        update_message,
                        update_action_title,
                        update_mode,
                        update_title,
                        update_icon,
                        update_is_ad,
                        target_id,
                        ads_frequency,
                        ads_limit,
                        app_version_code
                );

                String ads_platform  = mFlurryConfig.getString("ads_platform","admod");
                String show_banner = "1";
                String show_inter = "1";
                String show_native  =  mFlurryConfig.getString("show_native","1");
                String show_reward  = mFlurryConfig.getString("show_reward","1");
                String inter_start  = mFlurryConfig.getString("inter_start","0");
                String inter_delay  = mFlurryConfig.getString("inter_delay","30");
                String banner_id  = mFlurryConfig.getString("banner_id", AdsLoaded.BANNER_IN_APP_ID);
                String inter_id  = mFlurryConfig.getString("inter_id", AdsLoaded.INTER_IN_APP_ID);
                String native_id  = mFlurryConfig.getString("native_id", AdsLoaded.NATIVE_IN_APP_ID);
                String reward_id  = mFlurryConfig.getString("reward_id","0");
                String ads_id  = mFlurryConfig.getString("ads_id","0");

                if(!inter_id.equals(AdsLoaded.get().getInAppInterId())){
                    MyAds.clearInterAds();
                }

                AdsLoaded.get().setInApp(
                        ads_platform,show_banner,show_inter,show_native,show_reward,inter_start,inter_delay,banner_id,
                        inter_id,native_id,reward_id,ads_id
                );

                String s_ads_platform  = mFlurryConfig.getString("s_ads_platform","admod");
                String s_show_banner = mFlurryConfig.getString("s_show_banner","1");
                String s_show_inter = mFlurryConfig.getString("s_show_inter","1");
                String s_show_native  =  mFlurryConfig.getString("s_show_native","1");
                String s_show_reward  = mFlurryConfig.getString("s_show_reward","1");
                String s_banner_id  = mFlurryConfig.getString("s_banner_id", AdsLoaded.BANNER_SYSTEM_ID);
                String s_inter_id  = mFlurryConfig.getString("s_inter_id", AdsLoaded.INTER_SYSTEM_ID);
                String s_native_id  = mFlurryConfig.getString("s_native_id", AdsLoaded.NATIVE_SYSTEM_ID);
                String s_reward_id  = mFlurryConfig.getString("s_reward_id","0");

                AdsLoaded.get().setSystem(
                        s_ads_platform,s_show_banner,s_show_inter,s_show_native,s_show_reward,s_banner_id,s_inter_id,s_native_id,
                        s_reward_id);

                if(BuildConfig.DEBUG){
                    String builder = "update_link  " + update_link + "\n" +
                            "update_message  " + update_message + "\n" +
                            "update_action_title  " + update_action_title + "\n" +
                            "update_mode  " + update_mode + "\n" +
                            "update_title  " + update_title + "\n" +
                            "update_icon  " + update_icon + "\n" +
                            "update_is_ad  " + update_is_ad + "\n" +
                            "target_id  " + target_id + "\n" +
                            "ads_frequency  " + ads_frequency + "\n" +
                            "ads_limit  " + ads_limit + "\n" +
                            "app_version_code  " + app_version_code + "\n\n" +
                            "ads_platform  " + ads_platform + "\n" +
                            "show_banner  " + show_banner + "\n" +
                            "show_inter  " + show_inter + "\n" +
                            "show_native  " + show_native + "\n" +
                            "show_reward  " + show_reward + "\n" +
                            "inter_start  " + inter_start + "\n" +
                            "inter_delay  " + inter_delay + "\n" +
                            "banner_id  " + banner_id + "\n" +
                            "inter_id  " + inter_id + "\n" +
                            "native_id  " + native_id + "\n" +
                            "reward_id  " + reward_id + "\n" +
                            "ads_id  " + ads_id + "\n\n" +
                            "s_ads_platform  " + s_ads_platform + "\n" +
                            "s_show_banner  " + s_show_banner + "\n" +
                            "s_show_inter  " + s_show_inter + "\n" +
                            "s_show_native  " + s_show_native + "\n" +
                            "s_show_reward  " + s_show_reward + "\n" +
                            "s_banner_id  " + s_banner_id + "\n" +
                            "s_inter_id  " + s_inter_id + "\n" +
                            "s_native_id  " + s_native_id + "\n" +
                            "s_reward_id  " + s_reward_id + "\n";

                    DebugLog.e(TAG, builder);

                }

                if(callback!=null){
                    callback.callBack(0,0);
                    callback = null;
                }

                mFlurryConfig.unregisterListener(this);
            }
        });
        mFlurryConfig.fetchConfig();
    }


}
