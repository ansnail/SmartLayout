package top.androidman.autolayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class AutoData {
    private int design;     //设计图的设计尺寸
    private boolean width;  //以宽为基准进行缩放
    private boolean height; //以高为基准进行缩放，如果同时设置或同时未设置的时候以宽为准
    private boolean ignore; //是否忽略状态栏高度
    private int multiple; //几倍图，设计师进行设计的时候会确定

    public int getDesign() {
        return design;
    }

    public void setDesign(int design) {
        this.design = design;
    }

    public boolean isWidth() {
        return width;
    }

    public void setWidth(boolean width) {
        this.width = width;
    }

    public boolean isHeight() {
        return height;
    }

    public void setHeight(boolean height) {
        this.height = height;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }
}
