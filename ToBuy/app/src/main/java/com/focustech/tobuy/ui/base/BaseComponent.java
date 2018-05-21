package com.focustech.tobuy.ui.base;

import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;

/**
 * Created by Administrator on 2018/4/22.
 */

public class BaseComponent {

    protected final int MP = -1;
    protected final int WP = -2;

    protected final int SCREEN_WIDTH = EBApplication.screenSize.get("SCREEN_WIDTH");
    protected final int SCREEN_HEIGHT = EBApplication.screenSize.get("SCREEN_HEIGHT");

    /**
     * default
     * MATCH_PARENT = -1;
     * WRAP_CONTENT = -2;
     */
    protected LinearLayout.LayoutParams getLLP(int width, int height, float weight){
        return new LinearLayout.LayoutParams(width, height, weight);
    }
    protected LinearLayout.LayoutParams getLLP(int width, int height){
        return new LinearLayout.LayoutParams(width, height, 1);
    }
    protected LinearLayout.LayoutParams getLLPN(int width, int height){
        return new LinearLayout.LayoutParams(width, height);
    }

}
