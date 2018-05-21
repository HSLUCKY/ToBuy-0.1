package com.focustech.tobuy.biz.base;

/**
 * <基础业务类>
 *
 *  BasePresenter   实现  Presenter
 *  Presenter  接收   IMvpView    并对其操作
 *
 *   泛型类继承泛型接口
 *  提供IMVPView泛型接收前端Activity
 *  提供接收方法  attachView
 *  提供销毁方法  detachView
 *  提供获得类名方法    getName
 */
public abstract class BasePresenter<V extends IMvpView> implements Presenter<V> {
    protected V mvpView;

    @Override
    public void attachView(V view) {
        mvpView = view;
    }
    @Override
    public void detachView(V view) {
        mvpView = null;
    }
    @Override
    public String getName() {
        return mvpView.getClass().getSimpleName();
    }
}
