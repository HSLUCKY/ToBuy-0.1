package com.focustech.tobuy.ui.personcenter.component.home;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.view.MyTextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进行RecycleList子布局的动态初始化
 */
public class GoodsComponent {

    public static HashMap<LinearLayout, GoodsComponent> simpleGoods = new HashMap<>();
    public int goodsId = -1;

    private LinearLayout baseLinearLayout;
    private LinearLayout cLLayout;
    private LinearLayout tLLayout;
    private LinearLayout fLLayout;

    private ImageView headImageView;
    private MyTextView titleText;
    private MyTextView pcText;
    private MyTextView cText;

    public GoodsComponent(Context context) {

        baseLinearLayout = new LinearLayout(context);

        simpleGoods.put(baseLinearLayout, this);
        baseLinearLayout.setOnClickListener((View.OnClickListener)context);

        cLLayout = new LinearLayout(baseLinearLayout.getContext());
        tLLayout = new LinearLayout(baseLinearLayout.getContext());
        fLLayout = new LinearLayout(baseLinearLayout.getContext());

        baseLinearLayout.setLayoutParams(cmLayoutParams);
        baseLinearLayout.setPadding(10, 5, 10, 5);
        baseLinearLayout.setOrientation(LinearLayout.VERTICAL);

        cLLayout.setLayoutParams(tmLayoutParams);
        cLLayout.setOrientation(LinearLayout.VERTICAL);


        tLLayout.setLayoutParams(bmLayoutParams);
        tLLayout.setOrientation(LinearLayout.VERTICAL);

        fLLayout.setLayoutParams(bmLayoutParams);
        fLLayout.setOrientation(LinearLayout.HORIZONTAL);

        headImageView = new ImageView(context);
        headImageView.setAdjustViewBounds(true);
        headImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        headImageView.setLayoutParams(cmLayoutParams);


        titleText = new MyTextView(context);
        titleText.setText(new String("这是一件不错的商品"));
        titleText.setEllipsize(TextUtils.TruncateAt.END);
        titleText.setLayoutParams(txmLayoutParams);
        titleText.setTextSize(13);
        titleText.setIncludeFontPadding(false);
        titleText.setSingleLine(true);

        pcText = new MyTextView(context);
        pcText.setText(new String("￥19"));
        pcText.setLayoutParams(txmLayoutParams);
        pcText.setTextSize(16);
        pcText.setIncludeFontPadding(false);
        pcText.setSingleLine(true);
        pcText.setGravity(Gravity.LEFT);

        cText = new MyTextView(context);
        cText.setLayoutParams(txmLayoutParams);
        cText.setText(new String("19"));
        cText.setTextSize(16);
        cText.setIncludeFontPadding(false);
        cText.setGravity(Gravity.RIGHT);

        cLLayout.addView(headImageView);
        tLLayout.addView(titleText);
        fLLayout.addView(pcText);
        fLLayout.addView(cText);

        baseLinearLayout.addView(cLLayout);
        baseLinearLayout.addView(tLLayout);
        baseLinearLayout.addView(fLLayout);
    }


    private LinearLayout.LayoutParams cmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
    private LinearLayout.LayoutParams tmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.6f);
    private LinearLayout.LayoutParams bmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.1f);
    private LinearLayout.LayoutParams txmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);


    public LinearLayout getBaseLinearLayout() {
        return baseLinearLayout;
    }

    public MyTextView getTitleText() {
        return titleText;
    }

    public MyTextView getPcText() {
        return pcText;
    }

    public MyTextView getcText() {
        return cText;
    }

    public ImageView getHeadImageView() {
        return headImageView;
    }

}
