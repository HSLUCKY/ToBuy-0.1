package com.focustech.tobuy.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import com.focustech.tobuy.EBApplication;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/11.
 */

public final class PhoneParameterUtil {

    /**
     *  获取屏幕高和宽
     */
    public static HashMap<String, Integer> getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getResources().getDisplayMetrics();
        HashMap<String, Integer> screenSize = new HashMap<>();
        screenSize.put("SCREEN_WIDTH", displayMetrics.widthPixels);
        screenSize.put("SCREEN_HEIGHT", displayMetrics.heightPixels);
        return screenSize;
    }

    /**
     *  判断软键盘是否弹起
     */


    public static boolean isKeyboardShown(View rootView){
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        return r.bottom < EBApplication.screenSize.get("SCREEN_HEIGHT");
    }


}
