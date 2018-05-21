package com.focustech.tobuy.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.base.IMvpView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.ui.personcenter.activity.message.MessageActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.PersonalActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.SimplePersonalActivity;
import com.focustech.tobuy.ui.personcenter.activity.publish.PublishActivity;
import com.focustech.tobuy.ui.personcenter.activity.search.SearchActivity;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * <基础activity>
 *
 * @author caoyinfei
 * @version [版本号, 2014-3-24]
 * @see [相关类/方法]
 * @since [V1]
 */
public abstract class BaseActivity extends Activity implements CreateInit, PublishActivityCallBack, PresentationLayerFunc, IMvpView, OnClickListener {

    /***
     * ACTIVITY DATA
     */
    public ArrayList<Object> ACTIVITY_DATA;

    /**
     * APP当前用户实体
     */
    public static UserTable userTable;

    /**
     *APP用户头像
     */
    public static ImageView user_head;

    private PresentationLayerFuncHelper presentationLayerFuncHelper;

    /**
     * 用户个人信息，搜索
     */
    protected ImageView account, search;

    /**
     * 标题
     */
    protected MyTextView title;

    /**
     * 底部菜单
     */
    protected MyCircleImageView goods, community, message, me, publish;

    /**
     * 底部和顶部的布局
     */
    protected LinearLayout topTitle, footTitle;

    /**
     * 表现层
     */
    public BasePresenter presenter;

    /**
     * 获取APP基本窗口类名
     */
    public final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentationLayerFuncHelper = new PresentationLayerFuncHelper(this);
        initData();
        initViews();
        initListeners();
        EBApplication.ebApplication.addActivity(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initViews() {
        setHeader();
        setFooter();
    }

    @Override
    public void initListeners() {
        setHeaderListener();
        setFooterListener();
    }

    @Override
    public void setHeader() {
        topTitle = (LinearLayout) findViewById(R.id.topTitle);

        account = findViewById(R.id.head);
        if (user_head!=null){
            account.setImageDrawable(user_head.getDrawable());
        }else {
            account = findViewById(R.id.head);
        }
        search = (ImageView) findViewById(R.id.search);
        /*search.setImageResource(R.drawable.magnifire);*/

        title = findViewById(R.id.appTitle);
    }

    @Override
    public void setHeaderListener() {
        account.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void setFooter() {
        footTitle = (LinearLayout) findViewById(R.id.footTitle);

        goods = (MyCircleImageView) findViewById(R.id.goods);

        community = (MyCircleImageView) findViewById(R.id.community);

        message = (MyCircleImageView) findViewById(R.id.message);

        me = (MyCircleImageView) findViewById(R.id.me);

        publish = (MyCircleImageView) findViewById(R.id.publish);

    }


    @Override
    public void setFooterListener() {
        goods.setOnClickListener(this);
        community.setOnClickListener(this);
        message.setOnClickListener(this);
        me.setOnClickListener(this);
        publish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head:
                Bundle bundle = new Bundle();
                bundle.putInt("userId", BaseActivity.userTable.getId());
                startActivity(PersonalActivity.class, bundle);
                break;
            case R.id.search:
                startActivity(SearchActivity.class, null);
                break;
            case R.id.goods:
                startActivity(HomeActivity.class, null);
                break;
            case R.id.me:
                startActivity(SimplePersonalActivity.class, null);
                break;
            case R.id.message:
                startActivity(MessageActivity.class, null);
                break;
            case R.id.community:
                startActivity(CommunityActivity.class, null);
                break;
            case R.id.publish:
                startActivity(PublishActivity.class, null);
                break;
        }
    }

    public void onEventMainThread(Event event) {

    }

    @Override
    protected void onResume() {
        EBApplication.ebApplication.currentActivityName = this.getClass().getName();
        super.onResume();
    }

    @Override
    public void startActivity(Class<?> openClass, Bundle bundle) {
        if (EBApplication.currentActivityName.equals(openClass.getName()) && bundle == null) {
            return;
        }
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void startActivity(Class<?> openClass, int id) {
        if (EBApplication.currentActivityName.equals(openClass.getName())) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("keyId", id);
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void openActivityForResult(Class<?> openClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setResultOk(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) ;
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }


    //========================================showinfo==============================================

    @Override
    public void showToast(String msg) {
        presentationLayerFuncHelper.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        presentationLayerFuncHelper.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        presentationLayerFuncHelper.hideProgressDialog();
    }

    //==============================键盘显示隐藏====================================================
    @Override
    public void showSoftKeyboard(View focusView) {
        presentationLayerFuncHelper.showSoftKeyboard(focusView);
    }

    @Override
    public void hideSoftKeyboard() {
        presentationLayerFuncHelper.hideSoftKeyboard();
    }

    @Override
    public void hideSoftKeyboard(Context context, View view) {
        presentationLayerFuncHelper.hideSoftKeyboard(context, view);
    }

    //================================销毁窗口句柄==================================================
    @Override
    protected void onDestroy() {
        EBApplication.ebApplication.deleteActivity(this);
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView(this);
        }
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.cancelActivityRequest(TAG);
        super.onDestroy();
    }

    //===============================get set========================================================

    public LinearLayout getTopTitle() {
        return topTitle;
    }

    public void setTopTitle(LinearLayout topTitle) {
        this.topTitle = topTitle;
    }

    public LinearLayout getFootTitle() {
        return footTitle;
    }

    public void setFootTitle(LinearLayout footTitle) {
        this.footTitle = footTitle;
    }

}
