package top.androidman.autolayout;

import android.support.annotation.NonNull;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class AutoLayout {
    private static final AutoLayout ourInstance = new AutoLayout();
    private final AutoData smartData = new AutoData();

    public static AutoLayout init() {
        return ourInstance;
    }

    private AutoLayout() {
    }

    public AutoLayout design(@NonNull int dp){
        smartData.setDesign(dp);
        return this;
    }

    public AutoLayout width(){
        smartData.setWidth(true);
        return this;
    }

    public AutoLayout height(){
        smartData.setHeight(true);
        return this;
    }

    public AutoLayout ignore(){
        smartData.setIgnore(true);
        return this;
    }

    public AutoLayout multiple(int multiple){
        smartData.setMultiple(multiple);
        return this;
    }

    public AutoData getSmartData(){
        return smartData;
    }


}
