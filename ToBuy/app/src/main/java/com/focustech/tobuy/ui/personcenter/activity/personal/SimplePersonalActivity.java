package com.focustech.tobuy.ui.personcenter.activity.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.AccountActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.HistoryActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.PersonalActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.SaleGoodsActivity;
import com.focustech.tobuy.ui.personcenter.component.personal.SimplePersonalComponent;

/**
 * 个人中心
 */

public class SimplePersonalActivity extends BaseActivity {

    private int userId = -1;

    private LinearLayout headBackgroundLLayout;

    private LinearLayout accountInfoGuidLayout;
    private LinearLayout doInfoGuidLayoutFrRow;
    private LinearLayout doInfoGuidLayoutSeRow;

    private SimplePersonalComponent simplePersonalComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.simplepersonal_activity);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        initPersonalPage();
        setFooter();
    }

    @Override
    public void initListeners() {
        super.setFooterListener();
    }


    @Override
    public void onClick(View v) {

        if (v.equals(SimplePersonalComponent.head)){
            Bundle bundle2 = new Bundle();
            bundle2.putInt("userId", BaseActivity.userTable.getId());
            startActivity(PersonalActivity.class, bundle2);
        }

        if (SimplePersonalComponent.zones.keySet().contains(v)) {
            switch (SimplePersonalComponent.zones.get(v).getFuncName()) {
                case "我的帐号":
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", userId);
                    startActivity(AccountActivity.class, bundle);
                    break;
                case "浏览记录":
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("userId", userId);
                    startActivity(HistoryActivity.class, bundle2);
                    break;
                case "发布商品":
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("userId", userId);
                    startActivity(SaleGoodsActivity.class, bundle3);
                    break;
            }
        }

        super.onClick(v);
    }

    public void initPersonalPage() {
        this.headBackgroundLLayout = (LinearLayout) findViewById(R.id.headBackgroundLLayout);
        this.accountInfoGuidLayout = (LinearLayout) findViewById(R.id.accountInfoGruidLayout);
        this.doInfoGuidLayoutFrRow = (LinearLayout) findViewById(R.id.doInfoGuidLayoutFr);
        this.doInfoGuidLayoutSeRow = (LinearLayout) findViewById(R.id.doInfoGuidLayoutSe);

        this.simplePersonalComponent = new SimplePersonalComponent(this, accountInfoGuidLayout, doInfoGuidLayoutFrRow, doInfoGuidLayoutSeRow, headBackgroundLLayout);

        /**
         * 由于head是静态的所以要在初始化之后
         */
        this.simplePersonalComponent.getHead().setImageDrawable(BaseActivity.user_head.getDrawable());
        this.simplePersonalComponent.getHeadName().setText(BaseActivity.userTable.getUsername());
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
