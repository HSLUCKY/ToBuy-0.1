package com.focustech.tobuy.ui.base;

import android.os.Bundle;

/**
 * <页面跳转封装>
 *
 */
public interface PublishActivityCallBack {
    /**
     * 打开新界面
     *
     * @param openClass 新开页面
     * @param bundle    参数
     */
    public void startActivity(Class<?> openClass, Bundle bundle);

    /**
     * 通过主键打开界面
     */
    public void startActivity(Class<?> openClass, int id);

    /**
     * 打开新界面，期待返回
     *
     * @param openClass 新界面
     * @param requestCode 请求码
     * @param bundle 参数
     */
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle);

    /**
     * 返回到上个页面
     *
     * @param bundle 参数
     */
    public void setResultOk(Bundle bundle);
}
