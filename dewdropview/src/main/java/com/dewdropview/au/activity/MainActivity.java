package com.dewdropview.au.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.dewdropview.au.R;
import com.dewdropview.au.view.DewdropView;


public class MainActivity extends Activity {
    private DewdropView mView = null;
    private final int mViewWidth = 300;
    private final int mViewHeight = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        RelativeLayout root = (RelativeLayout) findViewById(R.id.root_view);

        mView = new DewdropView(MainActivity.this);
        root.addView(mView);
        mView.setImageResource(R.drawable.abc);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        lp.width = mViewWidth;
        lp.height = mViewHeight;
        mView.setSize(mViewWidth, mViewHeight);

//        mView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                System.out.print("event");
//                mView.touch(event);
//                return false;
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.print("event");
        if (mView != null) {
            mView.touch(event);
        }
        return super.onTouchEvent(event);

    }
}
