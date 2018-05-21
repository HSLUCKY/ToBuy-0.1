package com.focustech.tobuy.ui.personcenter.component.community;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/25.
 */

public class CardComponent extends BaseComponent {

    public static HashMap<LinearLayout, DetailCard> users = new HashMap<>();
    public static HashMap<LinearLayout, MessageComponent> message = new HashMap<>();

    private Context context;

    public CardComponent(Context context) {
        this.context = context;
    }


    public class DetailCard {

        private LinearLayout outLinearLayout;

        private LinearLayout titleLinearLayout;

        private LinearLayout ownerLinearLayout;
        private LinearLayout ownerLeftLinearLayout;
        private LinearLayout ownerRightLinearLayout;
        private LinearLayout ownerRightLinearLayoutFr;
        private LinearLayout ownerRightLinearLayoutSe;

        private LinearLayout contentLinearLayout;

        private LinearLayout picsLinearLayout;

        private LinearLayout sortLinearLayout;
        private LinearLayout sortLinearLayoutLf;
        private LinearLayout sortLinearLayoutRi;



        private MyTextView titleTx;

        private MyCircleImageView head;
        private MyTextView name;
        private MyTextView date;

        private MyTextView contentTx;

        private ImageView[] pics;

        private MyTextView sortLeft;
        private MyTextView sortRight;


        public DetailCard(int count) {
            outLinearLayout = new LinearLayout(context);

            titleLinearLayout = new LinearLayout(context);
            ownerLinearLayout = new LinearLayout(context);
            ownerLeftLinearLayout = new LinearLayout(context);
            ownerRightLinearLayout = new LinearLayout(context);
            ownerRightLinearLayoutFr = new LinearLayout(context);
            ownerRightLinearLayoutSe = new LinearLayout(context);

            contentLinearLayout = new LinearLayout(context);
            picsLinearLayout = new LinearLayout(context);
            sortLinearLayout = new LinearLayout(context);
            sortLinearLayoutLf = new LinearLayout(context);
            sortLinearLayoutRi = new LinearLayout(context);

            titleTx = new MyTextView(context);

            head = new MyCircleImageView(context);
            name = new MyTextView(context);
            date = new MyTextView(context);

            contentTx = new MyTextView(context);

            pics = new ImageView[count];
            for (int i = 0; i < pics.length; i++){
                pics[i] = new ImageView(context);
                pics[i].setLayoutParams(getLLPN(WP, WP));
            }

            sortLeft = new MyTextView(context);
            sortRight = new MyTextView(context);
            initLayout();
        }

        public void initLayout(){
            outLinearLayout.setLayoutParams(getLLP(MP, WP));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);
            outLinearLayout.setPadding(10, 20, 10, 0);

            titleLinearLayout.setLayoutParams(getLLP(MP, WP));
            titleLinearLayout.setPadding(0, 0, 0, 50);
            ownerLinearLayout.setLayoutParams(getLLP(MP, WP));
            ownerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            ownerLinearLayout.setGravity(Gravity.CENTER|Gravity.LEFT|Gravity.TOP);
            ownerLinearLayout.setPadding(0, 0, 0, 20);
            ownerLeftLinearLayout.setLayoutParams(getLLP(0, WP, 0.15f));
            ownerLeftLinearLayout.setGravity(Gravity.CENTER);
            //==================================================
            ownerLeftLinearLayout.setOnClickListener((View.OnClickListener)context);
            users.put(ownerLeftLinearLayout, this);
            //==================================================
            ownerRightLinearLayout.setLayoutParams(getLLP(0, WP, 0.85f));
            ownerRightLinearLayout.setOrientation(LinearLayout.VERTICAL);
            ownerRightLinearLayout.setGravity(Gravity.CENTER);
            ownerRightLinearLayoutFr.setLayoutParams(getLLP(MP, MP));
            ownerRightLinearLayoutFr.setGravity(Gravity.CENTER);
            ownerRightLinearLayoutFr.setPadding(50, 0, 0, 0);
            ownerRightLinearLayoutSe.setLayoutParams(getLLP(MP,MP));
            ownerRightLinearLayoutSe.setGravity(Gravity.CENTER);
            ownerRightLinearLayoutSe.setPadding(50, 0, 0, 0);

            contentLinearLayout.setLayoutParams(getLLP(MP, WP));
            contentLinearLayout.setGravity(Gravity.TOP);
            contentLinearLayout.setPadding(0, 0, 0, 20);

            picsLinearLayout.setLayoutParams(getLLP(MP, WP));
            picsLinearLayout.setGravity(Gravity.CENTER);
            picsLinearLayout.setOrientation(LinearLayout.VERTICAL);

            sortLinearLayout.setLayoutParams(getLLP(MP, 100));
            sortLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            sortLinearLayout.setGravity(Gravity.CENTER);
            sortLinearLayoutLf.setLayoutParams(getLLP(WP, WP));
            sortLinearLayoutLf.setGravity(Gravity.LEFT|Gravity.CENTER);
            sortLinearLayoutRi.setLayoutParams(getLLP(WP, WP));
            sortLinearLayoutRi.setGravity(Gravity.RIGHT|Gravity.CENTER);
            initComponent();
        }
        public void initComponent(){
            titleTx.setLayoutParams(getLLP(MP,WP));
            titleTx.setTextSize(20);
            titleTx.setMaxLines(1);
            titleTx.setGravity(Gravity.LEFT);
            titleTx.setText("标题");

            head.setLayoutParams(getLLP(WP,WP));
            head.setImageResource(R.drawable.neko);
            name.setLayoutParams(getLLP(MP, WP));
            name.setText("用户名");
            name.setTextSize(15);
            name.setMaxLines(1);
            name.setGravity(Gravity.LEFT);
            date.setLayoutParams(getLLP(MP, WP));
            date.setGravity(Gravity.LEFT);
            date.setText("2018-4-25");
            date.setMaxLines(1);

            contentTx.setLayoutParams(getLLP(MP, WP));
            contentTx.setGravity(Gravity.LEFT|Gravity.TOP);
            contentTx.setText("这是帖子的内容");
            contentTx.setTextSize(20);

            sortLeft.setText("全部回复");
            sortLeft.setTextSize(13);
            sortLeft.setMaxLines(1);
            sortLeft.setGravity(Gravity.LEFT);

            sortRight.setText("（+ _ +）");
            sortRight.setTextSize(20);
            sortRight.setMaxLines(1);
            sortRight.setGravity(Gravity.RIGHT);

            assemble();
        }
        public void assemble(){
            outLinearLayout.addView(titleLinearLayout);
            outLinearLayout.addView(ownerLinearLayout);
            outLinearLayout.addView(contentLinearLayout);
            outLinearLayout.addView(picsLinearLayout);
            outLinearLayout.addView(sortLinearLayout);

            titleLinearLayout.addView(titleTx);
            ownerLinearLayout.addView(ownerLeftLinearLayout);
            ownerLinearLayout.addView(ownerRightLinearLayout);
            ownerRightLinearLayout.addView(ownerRightLinearLayoutFr);
            ownerRightLinearLayout.addView(ownerRightLinearLayoutSe);

            ownerLeftLinearLayout.addView(head);
            ownerRightLinearLayoutFr.addView(name);
            ownerRightLinearLayoutSe.addView(date);

            contentLinearLayout.addView(contentTx);

            for (int i = 0; i < pics.length; i++){
                picsLinearLayout.addView(pics[i]);
            }

            sortLinearLayout.addView(sortLinearLayoutLf);
            sortLinearLayout.addView(sortLinearLayoutRi);
            sortLinearLayoutLf.addView(sortLeft);
            sortLinearLayoutRi.addView(sortRight);
        }

        public LinearLayout getContentLinearLayout() {
            return contentLinearLayout;
        }

        public MyTextView getContentTx() {
            return contentTx;
        }

        public MyTextView getDate() {
            return date;
        }

        public MyCircleImageView getHead() {
            return head;
        }

        public MyTextView getName() {
            return name;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getOwnerLeftLinearLayout() {
            return ownerLeftLinearLayout;
        }

        public LinearLayout getOwnerLinearLayout() {
            return ownerLinearLayout;
        }

        public LinearLayout getOwnerRightLinearLayout() {
            return ownerRightLinearLayout;
        }

        public LinearLayout getOwnerRightLinearLayoutFr() {
            return ownerRightLinearLayoutFr;
        }

        public LinearLayout getOwnerRightLinearLayoutSe() {
            return ownerRightLinearLayoutSe;
        }

        public ImageView[] getPics() {
            return pics;
        }

        public LinearLayout getPicsLinearLayout() {
            return picsLinearLayout;
        }

        public MyTextView getSortLeft() {
            return sortLeft;
        }

        public LinearLayout getSortLinearLayout() {
            return sortLinearLayout;
        }

        public LinearLayout getSortLinearLayoutLf() {
            return sortLinearLayoutLf;
        }

        public LinearLayout getSortLinearLayoutRi() {
            return sortLinearLayoutRi;
        }

        public MyTextView getSortRight() {
            return sortRight;
        }

        public LinearLayout getTitleLinearLayout() {
            return titleLinearLayout;
        }

        public MyTextView getTitleTx() {
            return titleTx;
        }
    }

    public DetailCard getDetailCard(int count){
        return new DetailCard(count);
    }
    public MessageComponent getReply(int userId){
        MessageComponent messageComponent = new MessageComponent(context, userId);
        messageComponent.getHeadLayout().setOnClickListener((View.OnClickListener)context);
        message.put(messageComponent.getHeadLayout(), messageComponent);
        return messageComponent;

    }
}
