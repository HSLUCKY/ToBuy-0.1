package com.focustech.tobuy.ui.base;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.focustech.tobuy.util.ToastUtil;

/**
 * <页面基础公共功能实现>
 *
 */
public class PresentationLayerFuncHelper implements PresentationLayerFunc {

    private Context context;

    public PresentationLayerFuncHelper(Context context) {
        this.context = context;
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.makeText(context, msg);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showSoftKeyboard(final View focusView) {
        /**
         * 使用延迟避免键盘无法弹出
         * */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(focusView, 0);
            }
        }, 1000);
    }

    @Override
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void hideSoftKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
