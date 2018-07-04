package top.androidman.smartlayout;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.graphics.Rect;
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
            throw new SmartException("Please set design width");
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

        float targetDensity = getTargetDensity(appDisplayMetrics, smartData);
        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
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
        SmartData smartData = SmartLayout.init().getSmartData();;
        if (activity instanceof ISmartLayout){
            ISmartLayout iSmartLayout = (ISmartLayout) activity;
            smartData = iSmartLayout.custom();
        }
        return smartData;
    }


    /**
     * 获取缩放后的Density
     * @param displayMetrics
     * @param smartData
     * @return
     */
    private float getTargetDensity(DisplayMetrics displayMetrics, SmartData smartData){
        float targetDensity = 0;
        if (smartData.isWidth()){
            targetDensity = ((float) displayMetrics.widthPixels) / smartData.getDesign();
        }else if (smartData.isHeight()){
            if (smartData.getDesign() == 0 || smartData.getMultiple() == 0){
                throw new SmartException("when layout height, must set design and multiple");
            }
            if (smartData.isIgnore()){
                targetDensity = ((float) displayMetrics.heightPixels) / smartData.getDesign();
                android.util.Log.e("SmartLayout","targetDensity_yes = " + targetDensity);
            }else {
                int statusBarHeight = -1;
                //获取status_bar_height资源的ID
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                }
                android.util.Log.e("SmartLayout","statusBarHeight = " + statusBarHeight);
                targetDensity = ((float)(displayMetrics.heightPixels - statusBarHeight)) / (smartData.getDesign() - statusBarHeight / smartData.getMultiple());
                android.util.Log.e("SmartLayout","targetDensity_no = " + targetDensity);
            }
        }
        return targetDensity;
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
