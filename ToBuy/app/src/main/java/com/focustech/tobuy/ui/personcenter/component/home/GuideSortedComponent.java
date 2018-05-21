package com.focustech.tobuy.ui.personcenter.component.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GuideSortedComponent extends BaseComponent {

    private Context context;
    public static HashMap<LinearLayout, SimpleGoods> simpleGoods = new HashMap<>();


    public GuideSortedComponent(Context context) {
        this.context = context;
    }


    public class SimpleGoods {

        private int typeId;
        private int cardId;
        private LinearLayout outLinearLayout;

        private LinearLayout leftLinearLayout;
        private LinearLayout rightLinearLayout;
        private LinearLayout rightLinearLayout1;
        private LinearLayout rightLinearLayout2;
        private LinearLayout rightLinearLayout3;

        private ImageView goodsPic;
        private MyTextView titleTx;
        private MyTextView priceTx;
        private MyTextView tipTx;

        public SimpleGoods(){
            this.outLinearLayout = new LinearLayout(context);
            this.leftLinearLayout  = new LinearLayout(context);
            this.rightLinearLayout = new LinearLayout(context);
            this.rightLinearLayout1  = new LinearLayout(context);
            this.rightLinearLayout2 = new LinearLayout(context);
            this.rightLinearLayout3 = new LinearLayout(context);

            this.goodsPic = new ImageView(context);
            this.titleTx = new MyTextView(context);
            this.priceTx = new MyTextView(context);
            this.tipTx = new MyTextView(context);
            initLayout();
        }
        public void initLayout(){
            outLinearLayout.setLayoutParams(getLLPN(MP, (int)(SCREEN_HEIGHT*0.17)));
            outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            outLinearLayout.setGravity(Gravity.CENTER);
            outLinearLayout.setPadding(0,0,0,10);
            leftLinearLayout.setLayoutParams(getLLPN((int)(SCREEN_WIDTH*0.3), MP));
            leftLinearLayout.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = getLLPN((int)(SCREEN_WIDTH*0.7), MP);
            layoutParams.gravity = Gravity.CENTER;
            rightLinearLayout.setLayoutParams(layoutParams);
            rightLinearLayout.setOrientation(LinearLayout.VERTICAL);
            rightLinearLayout.setGravity(Gravity.CENTER|Gravity.CENTER);
            rightLinearLayout1.setLayoutParams(getLLP(MP, 0, 0.5f));
            rightLinearLayout1.setGravity(Gravity.LEFT|Gravity.CENTER);
            rightLinearLayout2.setLayoutParams(getLLP(MP, 0, 0.25f));
            rightLinearLayout2.setGravity(Gravity.LEFT);
            rightLinearLayout3.setLayoutParams(getLLP(MP, 0, 0.25f));
            rightLinearLayout3.setGravity(Gravity.LEFT);
            initComponent();
        }
        public void initComponent() {
            goodsPic.setLayoutParams(getLLP(MP, MP));
            goodsPic.setImageResource(R.drawable.neko);
            titleTx.setLayoutParams(getLLP(MP, MP));
            titleTx.setEllipsize(TextUtils.TruncateAt.END);
            titleTx.setGravity(Gravity.LEFT | Gravity.CENTER);
            titleTx.setIncludeFontPadding(false);
            titleTx.setText("商品标题");
            priceTx.setLayoutParams(getLLP(MP, MP));
            priceTx.setIncludeFontPadding(false);
            priceTx.setText("￥25");
            priceTx.setTextSize(20);

            tipTx.setLayoutParams(getLLP(MP, MP));
            tipTx.setIncludeFontPadding(false);
            tipTx.setEllipsize(TextUtils.TruncateAt.END);
            tipTx.setText("商品提示");

            assemble();
        }

        public void assemble(){
            outLinearLayout.addView(leftLinearLayout);
            outLinearLayout.addView(rightLinearLayout);
            rightLinearLayout.addView(rightLinearLayout1);
            rightLinearLayout.addView(rightLinearLayout2);
            rightLinearLayout.addView(rightLinearLayout3);

            leftLinearLayout.addView(goodsPic);
            rightLinearLayout1.addView(titleTx);
            rightLinearLayout2.addView(priceTx);
            rightLinearLayout3.addView(tipTx);

            initListener();
        }
        public void initListener(){
            this.outLinearLayout.setOnClickListener((View.OnClickListener)context);
            simpleGoods.put(outLinearLayout, this);
        }

        public ImageView getGoodsPic() {
            return goodsPic;
        }

        public LinearLayout getLeftLinearLayout() {
            return leftLinearLayout;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public MyTextView getPriceTx() {
            return priceTx;
        }

        public LinearLayout getRightLinearLayout1() {
            return rightLinearLayout1;
        }

        public LinearLayout getRightLinearLayout2() {
            return rightLinearLayout2;
        }

        public LinearLayout getRightLinearLayout3() {
            return rightLinearLayout3;
        }

        public LinearLayout getRightLinearLayout() {
            return rightLinearLayout;
        }

        public MyTextView getTipTx() {
            return tipTx;
        }

        public MyTextView getTitleTx() {
            return titleTx;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }
    }

    public SimpleGoods getSimpleGoods(){
        return new SimpleGoods();
    }

}
