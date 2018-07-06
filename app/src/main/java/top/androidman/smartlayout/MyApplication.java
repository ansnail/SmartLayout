package top.androidman.smartlayout;

import top.androidman.autolayout.AutoApplication;
import top.androidman.autolayout.AutoLayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class MyApplication extends AutoApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayout.init().width(1080).multiple(3);
    }

}
