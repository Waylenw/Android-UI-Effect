package com.copyqqscrollview.project.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 下拉后可以自动回弹的Scrollview
 *
 * Created by Waylenwang on 2015/8/26.
 */
public class SpringbackScrollView extends ScrollView {

    // y方向上当前触摸点的前一次记录位置
    private int previousY = 0;
    // y方向上的触摸点的起始记录位置
    private int startY = 0;
    // y方向上的触摸点当前记录位置
    private int currentY = 0;
    // y方向上两次移动间移动的相对距离
    private int deltaY = 0;

    // 第一个子视图
    private View childView;

    // 用于记录childView的初始位置
    private Rect topRect = new Rect();

    public SpringbackScrollView(Context context) {
        super(context);
    }

    public SpringbackScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpringbackScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (null == childView) {
            return super.dispatchTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) event.getY();
                previousY = startY;
                break;
            case MotionEvent.ACTION_MOVE:
                currentY = (int) event.getY();
                deltaY = previousY - currentY;
                previousY = currentY;

                if (0 == getScrollY()
                        || childView.getMeasuredHeight() - getHeight() <= getScrollY()) {
                    // 记录childView的初始位置
                    if (topRect.isEmpty()) {
                        topRect.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }

                    // 更新childView的位置
                    childView.layout(childView.getLeft(), childView.getTop()
                                    - deltaY / 3, childView.getRight(),
                            childView.getBottom() - deltaY / 3);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!topRect.isEmpty()) {
                    upDownMoveAnimation();
                    // 子控件回到初始位置
                    childView.layout(topRect.left, topRect.top, topRect.right,
                            topRect.bottom);
                }

                startY = 0;
                currentY = 0;
                topRect.setEmpty();
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    // 初始化上下回弹的动画效果
    private void upDownMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                childView.getTop(), topRect.top);
        animation.setDuration(200);
        animation.setInterpolator(new AccelerateInterpolator());
        childView.setAnimation(animation);
    }

}
