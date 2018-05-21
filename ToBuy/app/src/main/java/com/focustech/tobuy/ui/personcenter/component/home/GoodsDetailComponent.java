package com.focustech.tobuy.ui.personcenter.component.home;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;
import com.focustech.tobuy.view.MyHorizonScrollView;
import com.focustech.tobuy.view.MyTextView;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/25.
 */

public class GoodsDetailComponent extends BaseComponent {

    private Context context;
    public int goodsId = -1;

    public GoodsDetailComponent(Context context) {
        this.context = context;
    }

    public class GoodsShowLayout {

        private LinearLayout outLinearLayout;
        private LinearLayout showPicLinearLayout;
        private HorizontalScrollView picHScroll;
        private LinearLayout titleLinearLayout;
        private LinearLayout priceLinearLayout;

        private LinearLayout priceLinearLayoutFr;
        private LinearLayout priceLinearLayoutFrLeft;
        private LinearLayout priceLinearLayoutFrRight;

        private LinearLayout priceLinearLayoutSe;

        private ImageView[] showPics;

        private MyTextView titleTx;
        private MyTextView priceTx;
        private MyTextView goodsTipTx;
        private MyTextView dateTx;

        @RequiresApi(api = Build.VERSION_CODES.N)
        private GoodsShowLayout(int imageCount) {

            outLinearLayout = new LinearLayout(context);
            showPicLinearLayout = new LinearLayout(context);
            titleLinearLayout = new LinearLayout(context);
            priceLinearLayout = new LinearLayout(context);
            priceLinearLayoutFr = new LinearLayout(context);
            priceLinearLayoutFrLeft = new LinearLayout(context);
            priceLinearLayoutFrRight = new LinearLayout(context);
            priceLinearLayoutSe = new LinearLayout(context);

            picHScroll = new HorizontalScrollView(context);

            showPics = new ImageView[imageCount];
            for (int i = 0; i < showPics.length; i++) {
                showPics[i] = new ImageView(context);
                showPics[i].setImageResource(R.drawable.neko);
                showPics[i].setLayoutParams(getLLPN(EBApplication.screenSize.get("SCREEN_WIDTH"), MP));
            }

            titleTx = new MyTextView(context);
            priceTx = new MyTextView(context);
            goodsTipTx = new MyTextView(context);
            dateTx = new MyTextView(context);
            initLayout();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initLayout() {
            outLinearLayout.setLayoutParams(getLLP(MP, WP));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);

            showPicLinearLayout.setLayoutParams(getLLP(MP, (int) (SCREEN_HEIGHT * 0.5)));
            titleLinearLayout.setLayoutParams(getLLP(MP, WP));
            titleLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            titleLinearLayout.setGravity(Gravity.LEFT|Gravity.TOP);
            priceLinearLayout.setLayoutParams(getLLP(MP, WP));
            priceLinearLayout.setGravity(Gravity.LEFT);
            priceLinearLayout.setOrientation(LinearLayout.VERTICAL);

            priceLinearLayoutFr.setLayoutParams(getLLP(MP, WP));
            priceLinearLayoutFr.setOrientation(LinearLayout.HORIZONTAL);
            priceLinearLayoutFr.setGravity(Gravity.CENTER);
            priceLinearLayoutFrLeft.setLayoutParams(getLLP(MP, WP));
            priceLinearLayoutFrLeft.setGravity(Gravity.LEFT|Gravity.CENTER);
            priceLinearLayoutFrRight.setLayoutParams(getLLP(MP, WP));
            priceLinearLayoutFrRight.setGravity(Gravity.RIGHT|Gravity.CENTER);

            priceLinearLayoutSe.setLayoutParams(getLLP(MP, WP));
            priceLinearLayoutSe.setOrientation(LinearLayout.HORIZONTAL);
            priceLinearLayoutSe.setGravity(Gravity.LEFT | Gravity.CENTER);

            picHScroll.setLayoutParams(getLLP(MP, MP));
            picHScroll.setFocusableInTouchMode(true);
            picHScroll.setFillViewport(true);

            initComponent();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initComponent() {
            titleTx.setText("商品标题");
            titleTx.setTextSize(25);
            titleTx.setGravity(Gravity.LEFT);
            titleTx.setIncludeFontPadding(false);

            priceTx.setText("￥15");
            priceTx.setTextSize(25);
            priceTx.setIncludeFontPadding(false);
            priceTx.setGravity(Gravity.LEFT);
            priceTx.setMaxLines(1);

            dateTx.setText(String.valueOf("\t\t\t\t\t\t" + (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())));
            dateTx.setTextSize(15);
            dateTx.setIncludeFontPadding(true);
            dateTx.setGravity(Gravity.RIGHT);

            goodsTipTx.setText("卖家提示");
            goodsTipTx.setTextSize(15);
            goodsTipTx.setGravity(Gravity.LEFT);
            goodsTipTx.setIncludeFontPadding(false);

            assemble();
        }

        private void assemble() {

            outLinearLayout.addView(showPicLinearLayout);
            outLinearLayout.addView(titleLinearLayout);
            outLinearLayout.addView(priceLinearLayout);
            showPicLinearLayout.addView(picHScroll);

            LinearLayout showPicsLinearLayout = new LinearLayout(context);
            showPicsLinearLayout.setLayoutParams(getLLP(MP, MP));
            showPicsLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i < showPics.length; i++) {
                showPicsLinearLayout.addView(showPics[i]);
            }
            picHScroll.addView(showPicsLinearLayout);

            titleLinearLayout.addView(titleTx);
            priceLinearLayout.addView(priceLinearLayoutFr);
            priceLinearLayout.addView(priceLinearLayoutSe);

            priceLinearLayoutFr.addView(priceLinearLayoutFrLeft);
            priceLinearLayoutFr.addView(priceLinearLayoutFrRight);
            priceLinearLayoutFrLeft.addView(priceTx);
            priceLinearLayoutFrRight.addView(dateTx);
            priceLinearLayoutSe.addView(goodsTipTx);

        }

        public MyTextView getGoodsTipTx() {
            return goodsTipTx;
        }

        public MyTextView getPriceTx() {
            return priceTx;
        }

        public ImageView[] getShowPics() {
            return showPics;
        }

        public MyTextView getTitleTx() {
            return titleTx;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public MyTextView getDateTx() {
            return dateTx;
        }
    }

    public class IntroduceGuide{

        private LinearLayout guideLinearLayout;
        private MyTextView buyCommentTx;

        public IntroduceGuide() {
            guideLinearLayout = new LinearLayout(context);
            buyCommentTx = new MyTextView(context);
            initLayout();
        }

        public void initLayout(){
            guideLinearLayout.setLayoutParams(getLLP(MP, (int)(SCREEN_HEIGHT * 0.05)));
            guideLinearLayout.setGravity(Gravity.CENTER);
            component();
        }
        public void component(){

            buyCommentTx.setText("评论");
            buyCommentTx.setTextSize(20);

            assemble();
        }
        public void assemble(){
            guideLinearLayout.addView(buyCommentTx);

        }

        public MyTextView getBuyCommentTx() {
            return buyCommentTx;
        }

        public LinearLayout getGuideLinearLayout() {
            return guideLinearLayout;
        }

    }

    public class IntroduceLayout {
        private MessageComponent messageComponent;


        public IntroduceLayout() {
            messageComponent = new MessageComponent(context, 0);
            initLayout();
        }

        public void initLayout(){
            messageComponent.getHeadLayout().setLayoutParams(getLLP(0, WP, 0.2f));
            messageComponent.getMessageOutLinearLayout().setLayoutParams(getLLP(0, WP, 0.8f));
            initComponent();
        }
        public void initComponent(){
            assemble();
        }
        public void assemble(){
        }

        public MessageComponent getMessageComponent() {
            return messageComponent;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public GoodsShowLayout getGoodsShowLayout(int imageCount) {
        return new GoodsShowLayout(imageCount);
    }

    public IntroduceGuide getIntroduceGuide(){
        return new IntroduceGuide();
    }

    public IntroduceLayout getIntroduceLayout(){
        return new IntroduceLayout();
    }

}
