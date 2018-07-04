package top.androidman.smartlayout;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import top.androidman.autolayout.AutoData;
import top.androidman.autolayout.IAutoLayout;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        TextView left = findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                Toast.makeText(MainActivity.this, "statusBarHeight = " + statusBarHeight, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "displayMetrics = " + displayMetrics.heightPixels, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
