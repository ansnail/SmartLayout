package top.androidman.autolayout;

/**
 * Created by yanjie on 2018-07-03.
 * Describe:
 */
public class AutoData {
    private boolean width = false;      //以宽为基准进行缩放
    private int widthNum = 0;       //设计图宽
    private boolean height = false;     //以高为基准进行缩放，如果同时设置或同时未设置的时候以宽为准
    private int heightNum = 0;      //设计图高
    private boolean ignore = false;     //是否忽略状态栏高度
    private int multiple = 0;       //几倍图，设计师进行设计的时候会确定

    public boolean isWidth() {
        return width;
    }

    public void setWidth(boolean width) {
        this.width = width;
    }

    public int getWidthNum() {
        return widthNum;
    }

    public void setWidthNum(int widthNum) {
        this.widthNum = widthNum;
    }

    public boolean isHeight() {
        return height;
    }

    public void setHeight(boolean height) {
        this.height = height;
    }

    public int getHeightNum() {
        return heightNum;
    }

    public void setHeightNum(int heightNum) {
        this.heightNum = heightNum;
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
