package com.focustech.tobuy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2018/4/25.
 */

public class MyHorizonScrollView extends HorizontalScrollView {
    private float x1 = 0, y1 = 0;
    private float x2 = 0, y2 = 0;
    public MyHorizonScrollView(Context context) {
        super(context);
    }

    public MyHorizonScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            x1 = ev.getX();
            y1 = ev.getY();
        }
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            x2 = ev.getX();
            y2 = ev.getY();
            if (x1 - x2 > 0) {
                //向左滑
                return true;
            } else if (x2 - x1 > 0) {
                //向右滑
                return true;
            }
        }
        if(ev.getAction() == MotionEvent.ACTION_UP) {
            x2 = ev.getX();
            y2 = ev.getY();
            if (x1 - x2 > 0) {
                //向左滑
                return true;
            } else if (x2 - x1 > 0) {
                //向右滑
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }


}
