package top.androidman.autolayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class AutoLayout {
    private static final AutoLayout ourInstance = new AutoLayout();
    private final AutoData autoData = new AutoData();

    public static AutoLayout init() {
        return ourInstance;
    }

    private AutoLayout() {
    }


    public AutoLayout width(int width){
        autoData.setWidthNum(width);
        autoData.setWidth(true);
        return this;
    }

    public AutoLayout height(int height){
        autoData.setHeightNum(height);
        autoData.setHeight(true);
        return this;
    }

    public AutoLayout ignore(){
        autoData.setIgnore(true);
        return this;
    }

    public AutoLayout multiple(int multiple){
        autoData.setMultiple(multiple);
        return this;
    }

    public AutoData getAutoData(){
        return autoData;
    }


}
