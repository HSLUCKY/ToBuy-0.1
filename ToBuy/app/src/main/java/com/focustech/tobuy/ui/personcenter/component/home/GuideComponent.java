package com.focustech.tobuy.ui.personcenter.component.home;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/2.
 */

public class GuideComponent extends BaseComponent {
    public static HashMap<ImageView, Integer> advertiseI = new HashMap<>();
    public static HashMap<LinearLayout, ComplexLayout.GuideLayout.Guide> guidesI = new HashMap<>();
    public static HashMap<LinearLayout, GoodsComponent> showsI = new HashMap<>();
    private ComplexLayout complexLayout;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GuideComponent(Context context, int count) {
        this.context = context;
        this.complexLayout = new ComplexLayout(count);
    }

    public class ComplexLayout {

        private LinearLayout baseLinearLayout;

        private AdvertisementLayout advertisementLayout;
        private GuideLayout guideLayout;
        private ShowLayout showLayout;

        public ComplexLayout(int count) {
            this.baseLinearLayout = new LinearLayout(context);

            this.advertisementLayout = new AdvertisementLayout(count);
            this.guideLayout = new GuideLayout();
            this.showLayout = new ShowLayout();

            this.initBaseLinearLayout();
            this.assemble();
        }

        private void initBaseLinearLayout() {
            baseLinearLayout.setLayoutParams(mmLayoutParams_W);
            baseLinearLayout.setOrientation(LinearLayout.VERTICAL);
        }
        private void assemble(){
            this.baseLinearLayout.addView(this.advertisementLayout.getAdvertisementHorizontalScrollView());
            this.baseLinearLayout.addView(this.guideLayout.getGuideLinearLayout());
            this.baseLinearLayout.addView(this.showLayout.getShowLinearLayout());
        }

        public class AdvertisementLayout {
            private HorizontalScrollView advertisementHorizontalScrollView;
            private LinearLayout advertisementLinearLayout;
            private ImageView[] pics;
            int count;

            public AdvertisementLayout(int count){
                this.count = count;
                this.advertisementHorizontalScrollView = new HorizontalScrollView(context);
                this.advertisementLinearLayout = new LinearLayout(context);
                this.initAdvertiseLayout();
            }

            private void initAdvertiseLayout(){
                advertisementHorizontalScrollView.setLayoutParams(mmLayoutParams_H3);
                advertisementHorizontalScrollView.setFillViewport(true);

                advertisementLinearLayout.setLayoutParams(mmLayoutParams);
                advertisementLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                advertisementLinearLayout.requestDisallowInterceptTouchEvent(true);

                this.pics = new ImageView[count];
                for (int i = 0; i < pics.length; i++){
                    pics[i] = new ImageView(context);
                    pics[i].setImageResource(R.drawable.neko);
                    pics[i].setLayoutParams(mmLayoutParams_W);
                    pics[i].setScaleType(ImageView.ScaleType.FIT_XY);
                    this.advertisementLinearLayout.addView(pics[i]);
                    advertiseI.put(pics[i], pics[i].getId());
                    pics[i].setOnClickListener((View.OnClickListener)context);
                }

                this.assemble();
            }

            private void assemble(){
                this.advertisementHorizontalScrollView.addView(this.advertisementLinearLayout);
            }

            public HorizontalScrollView getAdvertisementHorizontalScrollView() {
                return advertisementHorizontalScrollView;
            }

            public LinearLayout getAdvertisementLinearLayout() {
                return advertisementLinearLayout;
            }

            public ImageView[] getPics() {
                return pics;
            }
        }
        public class GuideLayout {

            private LinearLayout guideLinearLayout;
            private LinearLayout guideFrLinearLayout;
            private LinearLayout guideSeLinearLayout;

            private String[] guideTypes;
            private Guide[] guides;

            public GuideLayout(){
                guideLinearLayout = new LinearLayout(context);
                guideFrLinearLayout = new LinearLayout(context);
                guideSeLinearLayout = new LinearLayout(context);

                guideTypes = new String[]{"电器", "求助", "杂物", "活动", "悬赏", "特卖", "精品", "二手", "超低价", "限时"};
                guides = new Guide[10];
                for (int i = 0; i < 10; i++){
                    Guide guide = new Guide();
                    guide.getPic().setImageResource(R.drawable.guide1);
                    guide.getText().setText(guideTypes[i]);
                    this.guides[i] = guide;
                    guide.getOutLayout().setOnClickListener((View.OnClickListener)context);
                    guidesI.put(guide.getOutLayout(), guide);
                }
                this.initGuideLayout();
            }
            private void initGuideLayout(){
                guideLinearLayout.setLayoutParams(getLLPN(MP, (int)(SCREEN_HEIGHT*0.2)));
                guideLinearLayout.setPadding(10, 10, 10, 10);
                guideLinearLayout.setOrientation(LinearLayout.VERTICAL);

                guideFrLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                guideFrLinearLayout.setGravity(Gravity.CENTER);
                guideFrLinearLayout.setLayoutParams(getLLP(MP,MP));

                guideSeLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                guideSeLinearLayout.setGravity(Gravity.CENTER);
                guideSeLinearLayout.setLayoutParams(getLLP(MP, MP));
                this.assemble();
            }

            private void assemble(){
                this.guideLinearLayout.addView(this.guideFrLinearLayout);
                this.guideLinearLayout.addView(this.guideSeLinearLayout);
                for (int i = 0; i < 5; i++){
                    this.guideFrLinearLayout.addView(guides[i].getOutLayout());
                }
                for (int i = 5; i < 10; i++){
                    this.guideSeLinearLayout.addView(guides[i].getOutLayout());
                }
            }

            public class Guide {
                private LinearLayout outLayout;
                private LinearLayout picLayout;
                private LinearLayout txLayout;

                private MyCircleImageView pic;
                private MyTextView text;

                public Guide(){
                    this.outLayout = new LinearLayout(context);
                    this.picLayout = new LinearLayout(context);
                    this.txLayout = new LinearLayout(context);

                    this.pic = new MyCircleImageView(context);
                    this.text = new MyTextView(context);
                    this.initLayout();
                }
                private void initLayout(){
                    LinearLayout.LayoutParams layoutParams = getLLP(MP, MP);
                    layoutParams.gravity = Gravity.CENTER;
                    this.outLayout.setLayoutParams(layoutParams);
                    this.outLayout.setGravity(Gravity.CENTER);
                    this.outLayout.setOrientation(LinearLayout.VERTICAL);

                    this.picLayout.setLayoutParams(getLLP(150, 0, 0.3f));
                    this.picLayout.setGravity(Gravity.CENTER);

                    this.txLayout.setLayoutParams(getLLP(WP, 0, 0.1f));
                    this.txLayout.setGravity(Gravity.CENTER);



                    this.text.setGravity(Gravity.CENTER);
                    this.text.setPadding(0,0,10,0);
                    this.text.setIncludeFontPadding(false);
                    this.assemble();
                }

                private void assemble(){
                    this.outLayout.addView(this.picLayout);
                    this.outLayout.addView(this.txLayout);
                    this.picLayout.addView(this.pic);
                    this.txLayout.addView(this.text);
                }

                public ImageView getPic() {
                    return pic;
                }

                public LinearLayout getOutLayout() {
                    return outLayout;
                }

                public MyTextView getText() {
                    return text;
                }
            }

            public LinearLayout getGuideLinearLayout() {
                return guideLinearLayout;
            }

            public Guide[] getGuides() {
                return guides;
            }
        }
        public class ShowLayout {
            private LinearLayout showLinearLayout;
            private LinearLayout showFrLinearLayout;
            private LinearLayout showSeLinearLayout;
            private LinearLayout showTrLinearLayout;
            private GoodsComponent[] homeComponents;

            public ShowLayout(){
                showLinearLayout = new LinearLayout(context);
                showFrLinearLayout = new LinearLayout(context);
                showSeLinearLayout = new LinearLayout(context);
                showTrLinearLayout = new LinearLayout(context);
                this.homeComponents = new GoodsComponent[9];
                for (int i = 0; i < 9; i++){
                    GoodsComponent homeComponent = new GoodsComponent(context);
                    homeComponent.getHeadImageView().setImageResource(R.drawable.neko);
                    homeComponents[i] = homeComponent;

                    showsI.put(homeComponent.getBaseLinearLayout(), homeComponent);
                }
                this.initShowLayout();
            }
            private void initShowLayout(){
                showLinearLayout.setLayoutParams(mmLayoutParams_H6);
                showLinearLayout.setOrientation(LinearLayout.VERTICAL);

                showFrLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                showFrLinearLayout.setPadding(5, 5, 5, 0);
                showFrLinearLayout.setLayoutParams(mmLayoutParams_1);
                showSeLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                showSeLinearLayout.setPadding(5, 5, 5, 0);
                showSeLinearLayout.setLayoutParams(mmLayoutParams_1);
                showTrLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                showTrLinearLayout.setPadding(5, 5, 5, 0);
                showTrLinearLayout.setLayoutParams(mmLayoutParams_1);
                this.assemble();
            }
            private void assemble(){
                showLinearLayout.addView(showFrLinearLayout);
                showLinearLayout.addView(showSeLinearLayout);
                showLinearLayout.addView(showTrLinearLayout);

                for (int i = 0; i < 3; i++){
                    this.showFrLinearLayout.addView(homeComponents[i].getBaseLinearLayout());
                }
                for (int i = 3; i < 6; i++){
                    this.showSeLinearLayout.addView(homeComponents[i].getBaseLinearLayout());
                }
                for (int i = 6; i < 9; i++){
                    this.showTrLinearLayout.addView(homeComponents[i].getBaseLinearLayout());
                }
            }

            public GoodsComponent[] getHomeComponents() {
                return homeComponents;
            }

            public LinearLayout getShowLinearLayout() {
                return showLinearLayout;
            }

        }

        public LinearLayout getBaseLinearLayout() {
            return baseLinearLayout;
        }

        public ShowLayout getShowLayout() {
            return showLayout;
        }

        public GuideLayout getGuideLayout() {
            return guideLayout;
        }

        public AdvertisementLayout getAdvertisementLayout() {
            return advertisementLayout;
        }



        private LinearLayout.LayoutParams mmLayoutParams_W = new LinearLayout.LayoutParams(EBApplication.screenSize.get("SCREEN_WIDTH"), ViewGroup.LayoutParams.MATCH_PARENT);
        private LinearLayout.LayoutParams mmLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        private LinearLayout.LayoutParams mmLayoutParams_1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        private LinearLayout.LayoutParams mmLayoutParams_H3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (EBApplication.screenSize.get("SCREEN_HEIGHT") * 0.3));
        private LinearLayout.LayoutParams mmLayoutParams_H6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (EBApplication.screenSize.get("SCREEN_HEIGHT") * 0.6));


    }


    public ComplexLayout getComplexLayout() {
        return complexLayout;
    }
}
