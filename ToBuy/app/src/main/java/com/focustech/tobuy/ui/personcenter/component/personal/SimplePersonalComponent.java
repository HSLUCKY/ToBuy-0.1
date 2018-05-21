package com.focustech.tobuy.ui.personcenter.component.personal;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * SimplePersonalComponent：个人信息页面的布局控件容器类
 * ComplexLayout：功能子布局
 *
 * @author 黄升
 * @version 0.1
 * @date 2018.04.15
 */

public class SimplePersonalComponent extends BaseComponent {

    private Context context;
    public static HashMap<LinearLayout, ComplexLayout> zones = new HashMap<>();

    //设置布局======================================================================================
    //头像布局
    private LinearLayout headBackgroundLLayout;
    private LinearLayout midLLayout;
    private LinearLayout headLayout;

    //个人信息
    private LinearLayout accountInfoGuidLayout;
    //业务记录
    private LinearLayout doInfoGuidLayoutFrRow;

    //设置头像布局控件==============================================================================
    //圆形自定义头像View
    public static MyCircleImageView head;
    private MyTextView headName;

    private ComplexLayout myCount;
    private ComplexLayout myHistory;
    private ComplexLayout mySaleGoods;


    public SimplePersonalComponent(Context context, LinearLayout accountInfoGuidLayout, LinearLayout doInfoGuidLayoutFrRow, LinearLayout doInfoGuidLayoutSeRow, LinearLayout headBackgroundLLayout) {
        this.context = context;

        this.headBackgroundLLayout = headBackgroundLLayout;

        this.accountInfoGuidLayout = accountInfoGuidLayout;
        this.doInfoGuidLayoutFrRow = doInfoGuidLayoutFrRow;

        initHeadBackground();
    }

    private void initHeadBackground() {
        //实例化控件
        midLLayout = new LinearLayout(context);
        headLayout = new LinearLayout(context);
        head = new MyCircleImageView(context);
        headName = new MyTextView(context);

        //初始化参数================================================================================
        LinearLayout.LayoutParams headBcLayoutParam = getLLPN(MP, WP);
        headBcLayoutParam.gravity = Gravity.CENTER;
        headBackgroundLLayout.setLayoutParams(headBcLayoutParam);
        headBackgroundLLayout.setGravity(Gravity.CENTER);
        midLLayout.setLayoutParams(getLLPN((int) (SCREEN_WIDTH* 0.2), WP));
        midLLayout.setOrientation(LinearLayout.VERTICAL);
        headLayout.setLayoutParams(getLLPN(WP, WP));
        headLayout.setPadding(0, 250, 0, 0);

        head.setLayoutParams(getLLPN(ViewGroup.LayoutParams.MATCH_PARENT, 200));
        head.setImageResource(R.drawable.neko);
        head.setOnClickListener((View.OnClickListener)context);

        headName.setLayoutParams(getLLP(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        headName.setText("用户名");
        headName.setIncludeFontPadding(false);
        headName.setSingleLine(true);
        headName.setGravity(Gravity.CENTER);
        headName.setPadding(0, 0, 0, 150);

        //View控件装载==============================================================================
        headBackgroundLLayout.addView(midLLayout);
        midLLayout.addView(headLayout);
        midLLayout.addView(headName);
        headLayout.addView(head);

        myCount = new ComplexLayout("我的帐号", R.drawable.neko);
        myHistory = new ComplexLayout("浏览记录", R.drawable.neko);
        mySaleGoods = new ComplexLayout("发布商品", R.drawable.neko);

        zones.put(myCount.getOutLayout(), myCount);
        zones.put(myHistory.getOutLayout(), myHistory);
        zones.put(mySaleGoods.getOutLayout(), mySaleGoods);
        initPersonalGuide();
    }

    private void initPersonalGuide() {

        //View控件装载==============================================================================
        //PersonalGuide
        accountInfoGuidLayout.addView(myCount.getOutLayout());
        accountInfoGuidLayout.addView(myHistory.getOutLayout());
        accountInfoGuidLayout.addView(mySaleGoods.getOutLayout());
    }


    public class ComplexLayout {

        private String funcName;

        private LinearLayout outLayout;
        private LinearLayout picLayout;
        private LinearLayout txLayout;

        private ImageView funcPic;
        private MyTextView funcDesc;

        public ComplexLayout(String funcName, int picId) {
            this.funcName = funcName;
            initLayout();
            initComponent();
            builderViewHolder();
            funcPic.setImageResource(picId);
        }

        private void initLayout() {
            outLayout = new LinearLayout(context);
            picLayout = new LinearLayout(context);
            txLayout = new LinearLayout(context);

            outLayout.setOrientation(LinearLayout.VERTICAL);
            outLayout.setLayoutParams(getLLPN((int) (SCREEN_WIDTH * 0.333333), 250));
            outLayout.setOnClickListener((View.OnClickListener)context);
            picLayout.setLayoutParams(getLLP(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.3f));
            txLayout.setLayoutParams(getLLP(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.1f));
            txLayout.setGravity(Gravity.CENTER);
        }

        private void initComponent() {
            funcPic = new ImageView(context);
            funcDesc = new MyTextView(context);

            funcDesc.setGravity(Gravity.CENTER);
            funcDesc.setText(funcName);
            funcDesc.setIncludeFontPadding(false);

        }

        private void builderViewHolder() {
            outLayout.addView(picLayout);
            outLayout.addView(txLayout);
            picLayout.addView(funcPic);
            txLayout.addView(funcDesc);
        }

        public LinearLayout getOutLayout() {
            return outLayout;
        }

        public MyTextView getFuncDesc() {
            return funcDesc;
        }

        public String getFuncName() {
            return funcName;
        }

        public ImageView getFuncPic() {
            return funcPic;
        }

        public LinearLayout getPicLayout() {
            return picLayout;
        }

        public LinearLayout getTxLayout() {
            return txLayout;
        }
    }

    public ComplexLayout getMyCount() {
        return myCount;
    }

    public ComplexLayout getMyHistory() {
        return myHistory;
    }

    public LinearLayout getAccountInfoGuidLayout() {
        return accountInfoGuidLayout;
    }

    public static MyCircleImageView getHead() {
        return head;
    }

    public MyTextView getHeadName() {
        return headName;
    }
}
