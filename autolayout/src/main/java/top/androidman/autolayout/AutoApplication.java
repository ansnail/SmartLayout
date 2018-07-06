package top.androidman.autolayout;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public abstract class AutoApplication extends Application implements Application.ActivityLifecycleCallbacks{

    private float sNoncompatDensity;
    private float sNoncompatScaledDensity;


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AutoData autoData = getAutoData(activity);
        if (autoData == null){
            return;
        }
        if (autoData.getWidthNum() == 0 && autoData.getHeightNum() == 0){
            throw new AutoException("Please set design width or height");
        }
        if (autoData.getMultiple() == 0){
            throw new AutoException("Please set multiple , it is very important");
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

        float targetDensity = getTargetDensity(appDisplayMetrics, autoData);
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
     * @param activity 用户自定义的数据会覆盖全局数据
     * @return autoData
     */
    private AutoData getAutoData(Activity activity) {
        AutoData autoData = AutoLayout.init().getAutoData();
        if (activity instanceof IAutoLayout){
            IAutoLayout iAutoLayout = (IAutoLayout) activity;
            autoData = iAutoLayout.custom();
        }
        return autoData;
    }


    /**
     * 获取缩放后的Density
     * @param displayMetrics
     * @param autoData
     * @return
     */
    private float getTargetDensity(DisplayMetrics displayMetrics, AutoData autoData){
        float targetDensity = 0;
        float standardDp;
        if (autoData.isWidth()){
            standardDp = autoData.getWidthNum() / autoData.getMultiple();
            targetDensity = displayMetrics.widthPixels / standardDp;
        }else if (autoData.isHeight()){
            standardDp = autoData.getHeightNum() / autoData.getMultiple();
            if (autoData.isIgnore()){
                targetDensity = displayMetrics.heightPixels / standardDp;
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
                targetDensity = ((float)(displayMetrics.heightPixels - statusBarHeight)) / (standardDp - statusBarHeight / autoData.getMultiple());
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
