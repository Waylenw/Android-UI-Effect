package com.listindex.project.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.listindex.project.adapter.ItemAdapter;
import com.listindex.project.bean.User;

import java.util.ArrayList;

/**
 * 列表索引View
 *
 * @author Waylenwang
 *
 * 2015/8/27
 */
public class IndexView extends LinearLayout {

    private ArrayList<TextView> textViewList = new ArrayList<TextView>();

    private ListView listView;

    public IndexView(Context context) {
        super(context);
    }

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public IndexView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private int  lastIndex;
    public void init(final ListView listView, final TextView textView) {
        setOrientation(VERTICAL);
        this.listView=listView;
        //
        addTextView('#');
        for (int i = 0; i < 26; i++) {
            addTextView((char) ('A' + i));
        }

        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1500);
        animation.setFillAfter(true);

        final ListAdapter listAdapter = listView.getAdapter();

        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        int position = (int) (event.getY() * 27 / v.getHeight());
                        if (position >= 0 && position <= 26) {
                            char c;
                            int localPosition;
                            if (position == 0) {
                                c = '#';
//                                localPosition = 0;
                                listView.setSelection(0);
                            } else {
//                                c = (char) ('A' - 1 + position);
//                                localPosition = searchPosition(listAdapter, c);
                                searchAndSelectListView(position);
                            }
//                            if (localPosition != -1) {
//                                listView.setSelection(localPosition);
//                            }
                            lastIndex=position;
                            changeTextViewState(position,true);

                            textView.setText(ItemAdapter.IndexArrar[position]);
                            if (textView.getVisibility() == View.GONE) {
                                textView.setVisibility(View.VISIBLE);
                            }
                            textView.startAnimation(animation);
                        } else {
                            listView.setSelection(0);
                        }
                        setBackgroundColor(Color.GRAY);
                        break;
                    case MotionEvent.ACTION_UP:
                        setBackgroundColor(Color.WHITE);
                        changeTextViewState(lastIndex, false);
                        break;
                }
                return true;

            }
        });
    }

    /**
     * 修改索引的转态
     *
     * @param pos
     */
    public void changeTextViewState(int pos,boolean isClick) {
        for (int i = 0; i < textViewList.size(); i++) {
            if (i == pos) {
                textViewList.get(pos).setTextColor(isClick?Color.BLUE:Color.BLUE);
            } else {
                textViewList.get(i).setTextColor(isClick?Color.WHITE:Color.BLACK);
            }
        }


    }


    private void addTextView(char c) {
        final TextView textView = new TextView(getContext());
        textView.setText(String.valueOf(c));
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
//		textView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				textView.setTextColor(Color.YELLOW);
//			}
//		});
        LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0);
        lllp.weight = 1;
        textViewList.add(textView);
        addView(textView, lllp);
    }


    public void searchAndSelectListView(int indexViewPos){
     String type= ItemAdapter.IndexArrar[indexViewPos];
         int selectPos=0;
        for (int i = 0; i < listView.getAdapter().getCount(); i++) {
            if (type.equals(((User) (listView.getAdapter().getItem(i))).getType())) {
                selectPos=i;
                break;
            }
        }
        listView.setSelection(selectPos);
    }



}
