package com.focustech.tobuy.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyCheckBoxView extends BaseComponent {

    private static HashMap<LinearLayout, MyCheckBoxView> viewIds = new HashMap<LinearLayout, MyCheckBoxView>();

    private Context context;
    private int tagId;

    private LinearLayout outLinearLayout;
    private LinearLayout picLinearLayout;
    private LinearLayout txLinearLayout;
    private LinearLayout checkLinearLayout;

    private MyCircleImageView leftPic;
    private TextView midTx;
    private MyCircleImageView rightPic;

    private boolean CHECK_FLAG;

    public MyCheckBoxView(Context context) {
        this.context = context;
        this.outLinearLayout = new LinearLayout(context);
        this.picLinearLayout = new LinearLayout(context);
        this.txLinearLayout = new LinearLayout(context);
        this.checkLinearLayout = new LinearLayout(context);
        this.leftPic = new MyCircleImageView(context);
        this.midTx = new TextView(context);
        this.rightPic = new MyCircleImageView(context);
        initLayout();
    }

    private void initLayout(){
        outLinearLayout.setLayoutParams(getLLP(MP, MP));
        outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        outLinearLayout.setGravity(Gravity.CENTER);

        picLinearLayout.setLayoutParams(getLLP(MP, MP));
        picLinearLayout.setGravity(Gravity.CENTER|Gravity.RIGHT);

        txLinearLayout.setLayoutParams(getLLP(MP, MP));
        txLinearLayout.setGravity(Gravity.CENTER);

        checkLinearLayout.setLayoutParams(getLLP(MP, MP));
        checkLinearLayout.setGravity(Gravity.CENTER|Gravity.LEFT);

        initComponent();
    }

    private void initComponent(){
        leftPic.setImageResource(R.drawable.select);
        midTx.setGravity(Gravity.CENTER);
        midTx.setMaxLines(1);
        rightPic.setImageResource(R.drawable.select);
        rightPic.setVisibility(View.INVISIBLE);
        CHECK_FLAG = false;

        assemble();
    }

    private void assemble(){
        outLinearLayout.addView(picLinearLayout);
        outLinearLayout.addView(txLinearLayout);
        outLinearLayout.addView(checkLinearLayout);
        picLinearLayout.addView(leftPic);
        txLinearLayout.addView(midTx);
        checkLinearLayout.addView(rightPic);

        setCheckListenerId();
    }

    private void setCheckListenerId(){
        viewIds.put(this.outLinearLayout, this);
    }

    /***
     * 设置checkBox监听
     * @param onClickListener
     */
    public void setCheckListener(View.OnClickListener onClickListener){
        outLinearLayout.setOnClickListener(onClickListener);
    }

    public void switchStatus() {
        if (CHECK_FLAG){
            this.rightPic.setVisibility(View.INVISIBLE);
            CHECK_FLAG = false;
        }else {
            this.rightPic.setVisibility(View.VISIBLE);
            CHECK_FLAG = true;
        }
    }

    public boolean isCHECK_FLAG() {
        return CHECK_FLAG;
    }

    public LinearLayout getCheckLinearLayout() {
        return checkLinearLayout;
    }

    public Context getContext() {
        return context;
    }

    public MyCircleImageView getLeftPic() {
        return leftPic;
    }

    public TextView getMidTx() {
        return midTx;
    }

    public LinearLayout getOutLinearLayout() {
        return outLinearLayout;
    }

    public LinearLayout getPicLinearLayout() {
        return picLinearLayout;
    }

    public MyCircleImageView getRightPic() {
        return rightPic;
    }

    public LinearLayout getTxLinearLayout() {
        return txLinearLayout;
    }

    public static HashMap<LinearLayout, MyCheckBoxView> getViewIds() {
        return viewIds;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}

