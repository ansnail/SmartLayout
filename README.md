A low-cost Android screen adaptation solution (今日头条屏幕适配方案终极版，一个极低成本的 Android 屏幕适配方案).

### 具体的原理请看这里

[点我，点我](http://androidman.top/2018/07/04/android-autolayout/)

快速开始：

### 1 添加依赖

``` java
compile 'top.androidman.autolayout:autolayout:1.0.0'
```

### 2 初始化并开始使用
继承AutoApplication，然后在Application的方法里面根据自己的设计图设置相应的值即可

``` java
public class MyApplication extends AutoApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayout.init().width(1080).multiple(3);
    }
}

```
然后你的应用就可以自动适配了,是不是使用方法简单的令人发指。

![haha](http://androidman.top/img/post/2018-05-14-android-autolayout/i_am_so_diao.webp)

### 3 高级应用
(1)以高度为标准进行适配

``` java
public class MyApplication extends AutoApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayout.init().height(1080).multiple(3);
    }

}
```
(2)在某个activity中想改变全局的适配标准，比如全局是以宽为维度进行适配，在某个activity想改为以高为维度，只需要这个activity实现IAutoLayout接口即可，例如

``` java
public class MainActivity extends AppCompatActivity implements IAutoLayout{

    ....

    @Override
    public AutoData custom() {
        AutoData autoData = new AutoData();
        autoData.setHeight(true);
        autoData.setHeightNum(1920);
        autoData.setMultiple(3);
        return autoData;
    }
}
```

完全不影响现有的逻辑

(2)如果既不想以高为维度也不想以宽为维度进行适配，即这个页面不想自动进行适配的话，只需要把custom方法的返回值设置为null即可，例如：

``` java
public class MainActivity extends AppCompatActivity implements IAutoLayout{

    ....

    @Override
    public AutoData custom() {
        return null;
    }
}
```
(3)当以高为维度进行适配的时候，状态栏的高度将影响适配结果，所以会自动获取状态栏的高度，并将状态栏的高度减去，从而达到更好的适配结果。不过假如某些页面是全屏，即需要忽略状态栏。那么只需要多设置一个ignore方法即可，例如：

``` java
public class MyApplication extends AutoApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayout.init().height(1920).multiple(3).ignore();
    }

}
```
或者在某个单独页面设置
``` java
public class MainActivity extends AppCompatActivity implements IAutoLayout{

    ....

    @Override
    public AutoData custom() {
        AutoData autoData = new AutoData();
        autoData.setHeight(true);
        autoData.setHeightNum(1920);
        autoData.setMultiple(3);
        autoData.setIgnore(true);
        return autoData;
    }
}
```

最后放两张适配的效果图(设计稿宽度1080，高度1920，安装到小米Max手机):

适配前：&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

![适配前](http://androidman.top/img/post/2018-05-14-android-autolayout/auto_layout_1.webp)

适配后：

![适配后](http://androidman.top/img/post/2018-05-14-android-autolayout/auto_layout_2.webp)

布局文件如下：

``` xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_centerVertical="true"
        android:id="@+id/left"
        android:text="我是左边"
        android:textSize="24sp"
        android:gravity="center"
        android:textColor="@color/colorAccent"
        android:layout_width="180dp"
        android:layout_height="50dp"
        android:background="@color/colorPrimary" />

    <TextView
        android:layout_centerVertical="true"
        android:layout_toRightOf="@id/left"
        android:text="我是右边"
        android:textSize="24sp"
        android:gravity="center"
        android:textColor="@color/colorAccent"
        android:layout_width="180dp"
        android:layout_height="50dp"
        android:background="#77000000" />

    <TextView
        android:layout_centerHorizontal="true"
        android:id="@+id/top"
        android:text="我是上边"
        android:textSize="24sp"
        android:gravity="center"
        android:textColor="@color/colorAccent"
        android:layout_width="50dp"
        android:layout_height="311dp"
        android:background="@color/colorAccent" />

    <TextView
        android:layout_centerHorizontal="true"
        android:id="@+id/bottom"
        android:text="我是下边"
        android:textSize="24sp"
        android:gravity="center"
        android:textColor="@color/colorAccent"
        android:layout_width="50dp"
        android:layout_height="310dp"
        android:layout_below="@id/top"
        android:background="#77ff" />

</RelativeLayout>
```
