package com.wgt.admoblib.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.wgt.admoblib.R;

public class MyAdmobAds {

    private static InterstitialAd mInterstitialAd;

    public static int flagQC = 1;

    public static void initInterId(final Context ac) {

        if (mInterstitialAd == null)
            mInterstitialAd = new InterstitialAd(ac);

        String adUnitId = mInterstitialAd.getAdUnitId();

        if (adUnitId == null || adUnitId.equals("")) {
            mInterstitialAd.setAdUnitId(AdsConfig.getInterId());
        }
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

    }

    public static void clearInter(){
        mInterstitialAd = null;
    }

    public static boolean isInterLoaded(){
        return mInterstitialAd!=null&&mInterstitialAd.isLoaded();
    }

    private static void requestNewInterstitial() {
        AdRequest adRequest = getAdRequest();
        if (mInterstitialAd != null && !mInterstitialAd.isLoaded())
            mInterstitialAd.loadAd(adRequest);

    }

    private static AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public static void showAdsFull(final Callback callback) {

        if (mInterstitialAd == null) {
            callback.callBack(0, 0);
            return;
        }

        if (flagQC == 1) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestNewInterstitial();
                        flagQC = 0;
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable, AdsConfig.getTimeDelay());
                        callback.callBack(0, 0);
                    }
                });
                mInterstitialAd.show();
            } else {
                callback.callBack(0, 0);
                requestNewInterstitial();
            }
        } else {
            callback.callBack(0, 0);
            requestNewInterstitial();
        }
    }

    public static void showAdsFullNow(final Callback callback) {

        if (mInterstitialAd == null) {
            callback.callBack(0, 0);
            return;
        }
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    flagQC = 0;
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, AdsConfig.getTimeDelay());
                    callback.callBack(0, 0);
                }
            });

            mInterstitialAd.show();

        } else {
            callback.callBack(0, 0);
            requestNewInterstitial();
        }
    }

    public static AdSize getAdSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    public static void initBannerAds(final Activity ctx) {

        final AdView mAdViewBanner = new AdView(ctx);
        String adUnitId = mAdViewBanner.getAdUnitId();

        if (adUnitId == null || adUnitId.equals("")) {
            mAdViewBanner.setAdSize(getAdSize(ctx));
            mAdViewBanner.setAdUnitId(AdsConfig.getBannerId());
        }

        final RelativeLayout adViewContainer = ctx.findViewById(R.id.adView_container);
        adViewContainer.removeAllViews();
        try {
            adViewContainer.addView(mAdViewBanner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AdRequest adRequest = getAdRequest();

        TextView tvBannerLoading = ctx.findViewById(R.id.tv_banner_loading);
        tvBannerLoading.setMinimumHeight(getAdSize(ctx).getHeightInPixels(ctx));

        mAdViewBanner.loadAd(adRequest);
        mAdViewBanner.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                adViewContainer.setVisibility(View.GONE);
                tvBannerLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adViewContainer.setVisibility(View.VISIBLE);
                tvBannerLoading.setVisibility(View.GONE);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                adViewContainer.setVisibility(View.GONE);
                tvBannerLoading.setVisibility(View.VISIBLE);
                mAdViewBanner.loadAd(adRequest);
            }
        });
    }

    private static final Handler handler = new Handler();
    private  static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            flagQC = 1;
        }
    };


}
