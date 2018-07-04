package top.androidman.smartlayout;

import android.support.annotation.NonNull;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
class SmartLayout {
    private static final SmartLayout ourInstance = new SmartLayout();
    private final SmartData smartData = new SmartData();

    static SmartLayout init() {
        return ourInstance;
    }

    private SmartLayout() {
    }

    public SmartLayout design(@NonNull int dp){
        smartData.setDesign(dp);
        return this;
    }

    public SmartLayout width(){
        smartData.setWidth(true);
        return this;
    }

    public SmartLayout height(){
        smartData.setHeight(true);
        return this;
    }

    public SmartLayout ignore(){
        smartData.setIgnore(true);
        return this;
    }

    public SmartLayout multiple(int multiple){
        smartData.setMultiple(multiple);
        return this;
    }

    public SmartData getSmartData(){
        return smartData;
    }


}
