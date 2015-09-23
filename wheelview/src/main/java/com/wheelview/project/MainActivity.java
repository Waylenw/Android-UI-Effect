package com.wheelview.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Arrays;

public class MainActivity extends Activity implements OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final String[] PLANETS = new String[]{"选项一", "选项二", "选项三", "选项四", "选项五", "选项六", "选项七", "选项八"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_show_dialog_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        View outerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);//设置偏移量(选中框上面和下面显示个数)
        wv.setItems(Arrays.asList(PLANETS));
        wv.setSeletion(3);//设置选中的Item
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {

            @Override
            public void onSelected(int selectedIndex, String item) {
                 Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });

        new AlertDialog.Builder(MainActivity.this).setTitle("WheelView's Dialog").setView(outerView).setPositiveButton("关闭", null).show();
    }

}
