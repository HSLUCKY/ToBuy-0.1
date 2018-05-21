package com.focustech.tobuy.biz.base;

/**
 * <基础业务类>
 *     泛型接口接收实现了该泛型接口的Activity  接口中的方法就是交互通道
 *  此接口提供了基表现层类的功能
 */
public interface Presenter<V> {
    void attachView(V view);
    void detachView(V view);
    String getName();
}
