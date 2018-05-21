package com.focustech.tobuy.ui.base;

import android.content.Context;

/**
 * <公共方法抽象>
 *
 */
public interface CreateInit {
    /**
     * 初始化布局组件
     */
    public void initViews();

    /**
     * 增加按钮点击事件
     */
    void initListeners();

    /**
     * 初始化数据
     */
    public void initData();

    /**
     * 初始化公共头部
     */
    public void setHeader();

    /**
     * 初始化公共脚部
     */
    public void setFooter();

    /**
     * 设置头部监听
     */
    public void setHeaderListener();

    /**
     * 设置脚部监听
     */
    public void setFooterListener();
}

