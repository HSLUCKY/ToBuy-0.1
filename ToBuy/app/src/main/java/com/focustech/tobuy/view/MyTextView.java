package com.focustech.tobuy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/4/30.
 */

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
        this.setEnabled(true);
        this.setFocusable(true);
        this.setLongClickable(true);
        this.setTextIsSelectable(true);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
