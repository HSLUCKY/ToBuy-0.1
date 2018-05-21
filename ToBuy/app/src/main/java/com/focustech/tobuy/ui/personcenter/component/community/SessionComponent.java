package com.focustech.tobuy.ui.personcenter.component.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyHorizonScrollView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/20.
 */

public class SessionComponent extends BaseComponent {

    public static HashMap<ImageView, SimpleCardLayout> simpleCardLayoutHashMap = new HashMap<>();
    public static HashMap<TextView, Integer> sorts = new HashMap<>();
    public static int sortsCount = 0;

    private Context context;

    public SessionComponent(Context context){
        this.context = context;
    }

    public class GuideLayout{

        private LinearLayout zoneLinearLayout;
        //==========================================
        private LinearLayout bgLinearLayout;

        private LinearLayout sortLinearLayouts;
        private LinearLayout[] sortLinearLayout;

        private LinearLayout partLinearLayout;

        private LinearLayout tipLinearLayouts;
        private LinearLayout[] tipLinearLayout;

        private LinearLayout picLinearLayout;
        private LinearLayout countLinearLayout;
        //==========================================

        private MyCircleImageView sessionPic;

        private TextView memberCountTx;
        private TextView cardCountTx;

        private ImageView rankPic;
        private ImageView achievePic;
        private ImageView joinPic;

        private TextView allTx;
        private TextView newTx;
        private TextView hotTx;
        private TextView favoriteTx;

        public GuideLayout(){
            zoneLinearLayout = new LinearLayout(context);

            bgLinearLayout = new LinearLayout(context);

            sortLinearLayouts = new LinearLayout(context);
            sortLinearLayout = new LinearLayout[4];
            for(int i = 0; i < 4; i++){
                sortLinearLayout[i] = new LinearLayout(context);
            }

            partLinearLayout = new LinearLayout(context);

            tipLinearLayouts = new LinearLayout(context);
            tipLinearLayout = new LinearLayout[3];
            for(int i = 0; i < 3; i++){
                tipLinearLayout[i] = new LinearLayout(context);
            }

            picLinearLayout = new LinearLayout(context);
            countLinearLayout = new LinearLayout(context);

            sessionPic = new MyCircleImageView(context);

            memberCountTx = new TextView(context);
            cardCountTx = new TextView(context);

            rankPic = new ImageView(context);
            achievePic = new ImageView(context);
            joinPic = new ImageView(context);

            allTx = new TextView(context);
            newTx = new TextView(context);
            hotTx = new TextView(context);
            favoriteTx = new TextView(context);

            initLayout();
        }

        public void initLayout(){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int)(EBApplication.screenSize.get("SCREEN_HEIGHT") * 0.35));
            zoneLinearLayout.setLayoutParams(layoutParams);
            zoneLinearLayout.setOrientation(LinearLayout.VERTICAL);

            bgLinearLayout.setLayoutParams(mHLayoutParams7);
            bgLinearLayout.setOrientation(LinearLayout.VERTICAL);
            bgLinearLayout.setGravity(Gravity.BOTTOM);
            bgLinearLayout.setBackgroundColor(Color.RED);
            bgLinearLayout.setAlpha(0.7f);

            sortLinearLayouts.setLayoutParams(mHLayoutParams1);
            sortLinearLayouts.setOrientation(LinearLayout.HORIZONTAL);
            for(int i = 0; i < 4; i++){
                sortLinearLayout[i].setLayoutParams(mmLayoutParams);
                sortLinearLayout[i].setGravity(Gravity.CENTER);
            }

            partLinearLayout.setLayoutParams(mHLayoutParams4);
            partLinearLayout.setOrientation(LinearLayout.VERTICAL);

            tipLinearLayouts.setLayoutParams(mHLayoutParams1);
            tipLinearLayouts.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < 3; i++){
                tipLinearLayout[i].setLayoutParams(mmLayoutParams);
            }
            tipLinearLayout[0].setGravity(Gravity.RIGHT);
            tipLinearLayout[1].setGravity(Gravity.CENTER);
            tipLinearLayout[2].setGravity(Gravity.LEFT);

            picLinearLayout.setLayoutParams(getLLP(MP, 0, 0.4f));
            picLinearLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            countLinearLayout.setLayoutParams(mHLayoutParams1);
            countLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            countLinearLayout.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);

            initComponent();
        }

        public void initComponent(){

            sessionPic.setImageResource(R.drawable.neko);

            memberCountTx.setText("成员：828928");
            memberCountTx.setPadding(0,0,10,0);
            cardCountTx.setText("帖子：1021001");
            cardCountTx.setPadding(10,0,0,0);

            rankPic.setBackgroundColor(Color.RED);
            achievePic.setBackgroundColor(Color.RED);
            joinPic.setBackgroundColor(Color.RED);

            allTx.setText("全部");
            allTx.setTextSize(17);
            allTx.setOnClickListener((View.OnClickListener)context);

            newTx.setText("最新");
            newTx.setTextSize(17);
            newTx.setOnClickListener((View.OnClickListener)context);

            hotTx.setText("热门");
            hotTx.setTextSize(17);
            hotTx.setOnClickListener((View.OnClickListener)context);

            favoriteTx.setText("最爱");
            favoriteTx.setTextSize(17);
            favoriteTx.setOnClickListener((View.OnClickListener)context);

            sorts.put(allTx,SessionComponent.sortsCount);
            SessionComponent.sortsCount++;
            sorts.put(newTx,SessionComponent.sortsCount);
            SessionComponent.sortsCount++;
            sorts.put(hotTx,SessionComponent.sortsCount);
            SessionComponent.sortsCount++;
            sorts.put(favoriteTx,SessionComponent.sortsCount);
            SessionComponent.sortsCount++;

            assemble();
        }

        public void assemble(){
            zoneLinearLayout.addView(bgLinearLayout);
            zoneLinearLayout.addView(sortLinearLayouts);
            //=====================================================
            bgLinearLayout.addView(partLinearLayout);
            bgLinearLayout.addView(tipLinearLayouts);

            for (int i = 0; i < 4; i++){
                sortLinearLayouts.addView(sortLinearLayout[i]);

            }
            //=====================================================

            partLinearLayout.addView(picLinearLayout);
            partLinearLayout.addView(countLinearLayout);

            for (int i = 0; i < 3; i++){
                tipLinearLayouts.addView(tipLinearLayout[i]);
            }

            sortLinearLayout[0].addView(allTx);
            sortLinearLayout[1].addView(newTx);
            sortLinearLayout[2].addView(hotTx);
            sortLinearLayout[3].addView(favoriteTx);
            //=====================================================
            picLinearLayout.addView(sessionPic);
            countLinearLayout.addView(memberCountTx);
            countLinearLayout.addView(cardCountTx);

            tipLinearLayout[0].addView(rankPic);
            tipLinearLayout[1].addView(achievePic);
            tipLinearLayout[2].addView(joinPic);
        }

        public ImageView getAchievePic() {
            return achievePic;
        }

        public TextView getAllTx() {
            return allTx;
        }

        public LinearLayout getBgLinearLayout() {
            return bgLinearLayout;
        }

        public TextView getCardCountTx() {
            return cardCountTx;
        }

        public LinearLayout getCountLinearLayout() {
            return countLinearLayout;
        }

        public TextView getFavoriteTx() {
            return favoriteTx;
        }

        public TextView getHotTx() {
            return hotTx;
        }

        public ImageView getJoinPic() {
            return joinPic;
        }

        public TextView getMemberCountTx() {
            return memberCountTx;
        }

        public TextView getNewTx() {
            return newTx;
        }

        public LinearLayout getPartLinearLayout() {
            return partLinearLayout;
        }

        public LinearLayout getPicLinearLayout() {
            return picLinearLayout;
        }

        public ImageView getRankPic() {
            return rankPic;
        }

        public MyCircleImageView getSessionPic() {
            return sessionPic;
        }

        public LinearLayout[] getSortLinearLayout() {
            return sortLinearLayout;
        }

        public LinearLayout getSortLinearLayouts() {
            return sortLinearLayouts;
        }

        public LinearLayout[] getTipLinearLayout() {
            return tipLinearLayout;
        }

        public LinearLayout getTipLinearLayouts() {
            return tipLinearLayouts;
        }

        public LinearLayout getZoneLinearLayout() {
            return zoneLinearLayout;
        }
    }

    public class TipLayout{
        private LinearLayout outLinearLayout;
        private LinearLayout tagLinearLayout;
        private LinearLayout titleLinearLayout;

        private ImageView tagPic;
        private TextView titleTx;

        public TipLayout(){
            outLinearLayout = new LinearLayout(context);
            tagLinearLayout = new LinearLayout(context);
            titleLinearLayout = new LinearLayout(context);
            tagPic = new ImageView(context);
            titleTx = new TextView(context);
            initLayout();
        }
        public void initLayout(){
            outLinearLayout.setLayoutParams(getLLP(EBApplication.screenSize.get("SCREEN_WIDTH"), (int)(EBApplication.screenSize.get("SCREEN_HEIGHT")*0.05)));
            outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            tagLinearLayout.setLayoutParams(xwLayoutParams1);
            tagLinearLayout.setLayoutParams(getLLP((int)(EBApplication.screenSize.get("SCREEN_WIDTH")*0.1), MP));
            tagLinearLayout.setGravity(Gravity.CENTER);
            titleLinearLayout.setLayoutParams(getLLP((int)(EBApplication.screenSize.get("SCREEN_WIDTH")*0.9), MP));
            titleLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER);

            tagPic.setLayoutParams(getLLP(MP, WP));
            tagPic.setImageResource(R.drawable.message);
            titleTx.setLayoutParams(getLLP(MP, WP));
            titleTx.setGravity(Gravity.LEFT | Gravity.CENTER);
            titleTx.setText("板块须知");
            titleTx.setTextSize(15);
            assemble();
        }
        public void assemble(){
            outLinearLayout.addView(tagLinearLayout);
            outLinearLayout.addView(titleLinearLayout);
            tagLinearLayout.addView(tagPic);
            titleLinearLayout.addView(titleTx);
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getTagLinearLayout() {
            return tagLinearLayout;
        }

        public ImageView getTagPic() {
            return tagPic;
        }

        public LinearLayout getTitleLinearLayout() {
            return titleLinearLayout;
        }

        public TextView getTitleTx() {
            return titleTx;
        }
    }

    public class SimpleCardLayout {

        private int cardId = -1;
        private int type = -1;

        private LinearLayout outLinearLayout;

        private LinearLayout promulgatorLinearLayout;
        private LinearLayout promulgatorPicLinearLayout;
        private LinearLayout promulgatorTxLinearLayout;

        private LinearLayout cardLinearLayout;
        private LinearLayout titleLinearLayout;
        private MyHorizonScrollView showPicHorizontalScrollView;
        private MyHorizonScrollView tagHorizontalScrollView;

        private LinearLayout collectionLinearLayouts;
        private LinearLayout[] collectionLinearLayout;

        private ImageView promulgatorPic;
        private TextView promulgatorName;
        private TextView promulgatorTime;

        private TextView titleTx;
        private ImageView[] showPics;
        private TextView[] tagsTx;

        private TextView[] collectionsTx;

        public SimpleCardLayout(int count){

            outLinearLayout = new LinearLayout(context);

            promulgatorLinearLayout = new LinearLayout(context);
            promulgatorPicLinearLayout = new LinearLayout(context);
            promulgatorTxLinearLayout = new LinearLayout(context);

            cardLinearLayout = new LinearLayout(context);
            titleLinearLayout = new LinearLayout(context);
            showPicHorizontalScrollView = new MyHorizonScrollView(context);
            tagHorizontalScrollView = new MyHorizonScrollView(context);

            collectionLinearLayouts = new LinearLayout(context);
            collectionLinearLayout = new LinearLayout[3];
            for(int i = 0; i < 3; i++){
                collectionLinearLayout[i] = new LinearLayout(context);
            }

            promulgatorPic = new ImageView(context);
            promulgatorName = new TextView(context);
            promulgatorTime = new TextView(context);

            titleTx = new TextView(context);
            showPics = new ImageView[count];
            for(int i = 0; i < showPics.length; i++){
                showPics[i] = new ImageView(context);
                showPics[i].setOnClickListener((View.OnClickListener) context);
                simpleCardLayoutHashMap.put(showPics[i], this);
            }
            tagsTx = new TextView[4];
            for (int i = 0; i < 4; i++){
                tagsTx[i] = new TextView(context);
            }

            collectionsTx = new TextView[3];
            for(int i = 0; i < 3; i++){
                collectionsTx[i] = new TextView(context);
            }
            initLayout();
        }

        public void initLayout(){
            outLinearLayout.setLayoutParams(getLLP((int)(EBApplication.screenSize.get("SCREEN_WIDTH")), (int)(EBApplication.screenSize.get("SCREEN_HEIGHT") * 0.65)));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);

            promulgatorLinearLayout.setLayoutParams(getLLP(MP, 0, 0.1f));
            promulgatorLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            promulgatorLinearLayout.setGravity(Gravity.CENTER);
            promulgatorPicLinearLayout.setLayoutParams(getLLP(0, MP, 0.1f));
            promulgatorPicLinearLayout.setGravity(Gravity.LEFT|Gravity.CENTER);
            promulgatorTxLinearLayout.setLayoutParams(getLLP(0, MP, 0.5f));
            promulgatorTxLinearLayout.setOrientation(LinearLayout.VERTICAL);
            promulgatorTxLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER);

            cardLinearLayout.setLayoutParams(getLLP(MP, 0, 0.5f));
            cardLinearLayout.setOrientation(LinearLayout.VERTICAL);
            titleLinearLayout.setLayoutParams(getLLP(MP, 0, 0.2f));
            titleLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER);
            showPicHorizontalScrollView.setLayoutParams(getLLP(WP, 0, 0.7f));
            showPicHorizontalScrollView.setFillViewport(true);
            showPicHorizontalScrollView.requestDisallowInterceptTouchEvent(true);
            tagHorizontalScrollView.setLayoutParams(getLLP(WP, 0, 0.1f));
            tagHorizontalScrollView.setFillViewport(true);

            collectionLinearLayouts.setLayoutParams(getLLP(MP, 0, 0.1f));
            collectionLinearLayouts.setOrientation(LinearLayout.HORIZONTAL);
            for (int i = 0; i < 3; i++){
                collectionLinearLayout[i].setLayoutParams(getLLP(MP, MP));
                collectionLinearLayout[i].setGravity(Gravity.CENTER);
            }
            initComponent();
        }
        public void initComponent(){
            promulgatorPic.setLayoutParams(getLLP(WP, WP));
            promulgatorPic.setImageResource(R.drawable.message);
            promulgatorName.setText("用户名");
            promulgatorName.setGravity(Gravity.LEFT);
            promulgatorTime.setText("1天前");
            promulgatorTime.setGravity(Gravity.LEFT);

            titleTx.setText("帖子标题");
            titleTx.setGravity(Gravity.LEFT);
            for(int i = 0; i < showPics.length; i++){
                showPics[i].setImageResource(R.drawable.neko);
            }
            for (int i = 0; i < tagsTx.length; i++){
                tagsTx[i].setPadding(5, 5, 20, 5);
                tagsTx[i].setText("标签");
            }

            collectionsTx[0].setText("评论");
            collectionsTx[1].setText("好评");
            collectionsTx[2].setText("差评");
            assemble();
        }
        public void assemble(){
            outLinearLayout.addView(promulgatorLinearLayout);
            outLinearLayout.addView(cardLinearLayout);
            outLinearLayout.addView(collectionLinearLayouts);

            promulgatorLinearLayout.addView(promulgatorPicLinearLayout);
            promulgatorLinearLayout.addView(promulgatorTxLinearLayout);

            cardLinearLayout.addView(titleLinearLayout);
            cardLinearLayout.addView(showPicHorizontalScrollView);
            cardLinearLayout.addView(tagHorizontalScrollView);
            for(int i = 0; i < collectionLinearLayout.length; i++){
                collectionLinearLayouts.addView(collectionLinearLayout[i]);
            }

            promulgatorPicLinearLayout.addView(promulgatorPic);
            promulgatorTxLinearLayout.addView(promulgatorName);
            promulgatorTxLinearLayout.addView(promulgatorTime);
            titleLinearLayout.addView(titleTx);

            LinearLayout showLinearLayout = new LinearLayout(context);
            showLinearLayout.setLayoutParams(getLLP(MP, MP));
            showLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            showLinearLayout.setGravity(Gravity.CENTER);
            showPicHorizontalScrollView.addView(showLinearLayout);
            for (int i = 0; i < showPics.length; i++){
                showLinearLayout.addView(showPics[i]);
                showPics[i].setLayoutParams(getLLPN(EBApplication.screenSize.get("SCREEN_WIDTH"), MP));
                showPics[i].setScaleType(ImageView.ScaleType.FIT_XY);
            }

            LinearLayout tagLinearLayout = new LinearLayout(context);
            tagLinearLayout.setLayoutParams(getLLP(MP, MP));
            tagLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            tagLinearLayout.setGravity(Gravity.CENTER);

            tagHorizontalScrollView.addView(tagLinearLayout);
            for (int i = 0; i < tagsTx.length; i++){
                tagLinearLayout.addView(tagsTx[i]);
            }
            for (int i = 0; i < collectionsTx.length; i++){
                collectionLinearLayout[i].addView(collectionsTx[i]);
            }
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }

        public LinearLayout getCardLinearLayout() {
            return cardLinearLayout;
        }

        public LinearLayout[] getCollectionLinearLayout() {
            return collectionLinearLayout;
        }

        public LinearLayout getCollectionLinearLayouts() {
            return collectionLinearLayouts;
        }

        public TextView[] getCollectionsTx() {
            return collectionsTx;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getPromulgatorLinearLayout() {
            return promulgatorLinearLayout;
        }

        public TextView getPromulgatorName() {
            return promulgatorName;
        }

        public ImageView getPromulgatorPic() {
            return promulgatorPic;
        }

        public LinearLayout getPromulgatorPicLinearLayout() {
            return promulgatorPicLinearLayout;
        }

        public TextView getPromulgatorTime() {
            return promulgatorTime;
        }

        public LinearLayout getPromulgatorTxLinearLayout() {
            return promulgatorTxLinearLayout;
        }

        public HorizontalScrollView getShowPicHorizontalScrollView() {
            return showPicHorizontalScrollView;
        }

        public ImageView[] getShowPics() {
            return showPics;
        }

        public HorizontalScrollView getTagHorizontalScrollView() {
            return tagHorizontalScrollView;
        }

        public TextView[] getTagsTx() {
            return tagsTx;
        }

        public LinearLayout getTitleLinearLayout() {
            return titleLinearLayout;
        }

        public TextView getTitleTx() {
            return titleTx;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public GuideLayout getGuideLayout(){
        return new GuideLayout();
    }
    public TipLayout getTipLayout(){
        return new TipLayout();
    }
    public SimpleCardLayout getSimpleCardLayout(int count){
        return new SimpleCardLayout(count);
    }

    private LinearLayout.LayoutParams mHLayoutParams7 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.7f);
    private LinearLayout.LayoutParams mHLayoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.1f);
    private LinearLayout.LayoutParams mHLayoutParams4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.4f);
    private LinearLayout.LayoutParams mmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
    private LinearLayout.LayoutParams xwLayoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.1f);

}
