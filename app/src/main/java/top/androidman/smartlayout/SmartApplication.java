package top.androidman.smartlayout;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public abstract class SmartApplication extends Application implements Application.ActivityLifecycleCallbacks{

    private float sNoncompatDensity;
    private float sNoncompatScaledDensity;


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        SmartData smartData = getSmartData(activity);
        if (smartData.getDesign() == 0){
            throw new RuntimeException("Please set design width");
        }

        final DisplayMetrics appDisplayMetrics = getResources().getDisplayMetrics();

        if(sNoncompatDensity == 0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = getResources().getDisplayMetrics().scaledDensity;
                    }
                }
                @Override
                public void onLowMemory() {

                }
            });
        }

        float screenWith = 0;
        float screenHeight = 0;
        if (smartData.isWidth()){
            if (smartData.getDesign() == 0){
                throw new RuntimeException("Please set design width");
            }
            screenWith = appDisplayMetrics.widthPixels;
        }

        float targetDensity = ((float) appDisplayMetrics.widthPixels) / smartData.getDesign();


        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        android.util.Log.e("SmartLayout","activity_width = " + activityDisplayMetrics.widthPixels);
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

    }

    /**
     * 获取用户的设置数据
     * @param activity 用户自定义的
     * @return SmartData
     */
    @NonNull
    private SmartData getSmartData(Activity activity) {
        SmartData smartData = null;
        if (activity instanceof ISmartLayout){
            ISmartLayout iSmartLayout = (ISmartLayout) activity;
            smartData = iSmartLayout.custome();
        }
        if (smartData == null) {
            smartData = SmartLayout.init().getSmartData();
        }
        return smartData;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
