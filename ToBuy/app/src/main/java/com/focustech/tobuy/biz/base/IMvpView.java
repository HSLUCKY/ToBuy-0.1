package com.focustech.tobuy.biz.base;

/**
 * <功能详细描述>
 *  此接口提供了表现层对Activity进行交互的接口
 */
public interface IMvpView {
    void onError(String errorMsg, String code);

    void onSuccess();

    void showLoading();

    void hideLoading();
}
