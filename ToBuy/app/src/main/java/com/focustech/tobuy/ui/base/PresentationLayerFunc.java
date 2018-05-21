package com.focustech.tobuy.ui.base;

import android.content.Context;
import android.view.View;

/**
 * <页面基础公共功能抽象>
 *
 */
public interface PresentationLayerFunc {
    /**
     * 弹出消息
     *
     * @param msg
     */
    public void showToast(String msg);

    /**
     * 网络请求加载框
     */
    public void showProgressDialog();

    /**
     * 隐藏网络请求加载框
     */
    public void hideProgressDialog();

    /**
     * 显示软键盘
     *
     * @param focusView
     */
    public void showSoftKeyboard(View focusView);

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard();

    /**
     * 强制隐藏软键盘
     */
    public void hideSoftKeyboard(Context context, View view);
}
