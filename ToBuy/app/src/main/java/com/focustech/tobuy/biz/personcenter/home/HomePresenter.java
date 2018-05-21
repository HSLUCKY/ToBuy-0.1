package com.focustech.tobuy.biz.personcenter.home;

import com.focustech.tobuy.bean.service.goods.GetGoodsDetailResp;
import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.home.view.IHomeView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 用于加载主界面展示的商品
 * 预加载
 * 广告3个
 * 推荐9个
 * 展示不记
 * <p>
 * 每次加载
 * 每次上拉到底下加载8条
 * 每次刷新
 * <p>
 *
 * uuid 1 代表预加载
 * uuid 2 代表每次加载
 * uuid 3 代表按排序加载
 * uuid 4 代表加载具体商品信息
 * uuid 5 代表发布一条商品评论
 * Created by Administrator on 2018/5/1.
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    /**
     * 初次记载20个商品信息
     * 前三个为广告
     * 后9个为推广
     * 最后8个为一般信息
     */
    public void preLoad() {
        eachLoad(1, 0, 20);
    }

    /**
     * start 用于分页机制
     *
     * uuid用于区分同一个连接不同操作
     * 每次加载count个信息
     */
    public void eachLoad(final int uuid, int start, final int count) {
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        RequestParam rp = new RequestParam();
        rp.setUuid(uuid);
        rp.setStart(start);
        rp.setCount(count);
        rp.setView("home");
        rp.setMethod("eachLoad");
        Gson gson = new Gson();
        String str = gson.toJson(rp);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=home&method=eachLoad", getName(), new ITRequestResult<GetGoodsResp>() {
            @Override
            public void onSuccessful(GetGoodsResp entity) {
                if (uuid == 1){
                    mvpView.preLoadGoods(entity);
                    return;
                }
                mvpView.eachLoadGoods(entity, null);
            }
            @Override
            public void onFailure(String errorMsg) {
                //mvpView.onError(errorMsg, "");
            }
            @Override
            public void onCompleted() {

            }
        }, GetGoodsResp.class, new Param("rp", str));


    }

}

/**
 * 重新封装参数的响应视图
 */
class RequestParam implements Serializable{
    private int uuid;
    private int start;
    private int count;
    private String view;
    private String method;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}