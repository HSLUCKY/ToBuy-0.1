package com.focustech.tobuy.ui.personcenter.component.message;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MessageComponent extends BaseComponent {

    public static HashMap<LinearLayout, MessageComponent>  messages = new HashMap<>();
    public int userId = -1;

    private Context context;
    private LinearLayout messageLinearLayout;

    private LinearLayout headLayout;
    private MyCircleImageView headPic;

    private LinearLayout messageOutLinearLayout;
    private LinearLayout messageOutLinearLayoutFr;
    private LinearLayout messageOutLinearLayoutSe;

    private MyTextView messageTime;
    private MyTextView messageContent;

    public MessageComponent(Context context, int userId){
        this.context = context;
        this.userId = userId;
        this.messageLinearLayout = new LinearLayout(context);

        this.headLayout = new LinearLayout(context);
        this.headPic = new MyCircleImageView(context);

        this.messageOutLinearLayout = new LinearLayout(context);
        this.messageOutLinearLayoutFr = new LinearLayout(context);
        this.messageOutLinearLayoutSe = new LinearLayout(context);

        this.messageTime = new MyTextView(context);
        this.messageContent = new MyTextView(context);

        initLayout();
    }



    private void initLayout(){
        messageLinearLayout.setLayoutParams(getLLP(MP, WP));
        messageLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        headLayout.setLayoutParams(getLLP(0, WP, 0.2f));
        headLayout.setGravity(Gravity.CENTER);
        headLayout.setOnClickListener((View.OnClickListener)context);
        messages.put(headLayout, this);

        messageOutLinearLayout.setLayoutParams(getLLP(0, WP, 0.8f));
        messageOutLinearLayout.setOrientation(LinearLayout.VERTICAL);


        messageOutLinearLayoutFr.setLayoutParams(getLLP(MP, WP));
        messageOutLinearLayoutFr.setGravity(Gravity.LEFT|Gravity.TOP);

        messageOutLinearLayoutSe.setLayoutParams(getLLP(MP, MP));
        messageOutLinearLayoutSe.setGravity(Gravity.LEFT | Gravity.TOP);



        initComponent();

    }

    private void initComponent(){
        headPic.setLayoutParams(getLLP(MP, WP));
        headPic.setImageResource(R.drawable.neko);

        messageTime.setLayoutParams(getLLP(MP, WP));
        messageTime.setText("2018-04-20");
        messageTime.setIncludeFontPadding(true);
        messageTime.setMaxLines(1);

        messageContent.setLayoutParams(getLLP(WP, WP));
        messageContent.setText("你好这是一条信息\r\n这是第二行信息");

        assemble();
    }

    private void assemble(){
        messageLinearLayout.addView(headLayout);
        messageLinearLayout.addView(messageOutLinearLayout);

        headLayout.addView(headPic);
        messageOutLinearLayout.addView(messageOutLinearLayoutFr);
        messageOutLinearLayout.addView(messageOutLinearLayoutSe);

        messageOutLinearLayoutFr.addView(messageTime);
        messageOutLinearLayoutSe.addView(messageContent);
    }

    public MyCircleImageView getHeadPic() {
        return headPic;
    }

    public MyTextView getMessageContent() {
        return messageContent;
    }

    public LinearLayout getMessageLayout() {
        return messageLinearLayout;
    }

    public MyTextView getMessageTime() {
        return messageTime;
    }

    public LinearLayout getMessageLinearLayout() {
        return messageLinearLayout;
    }

    public LinearLayout getMessageOutLinearLayout() {
        return messageOutLinearLayout;
    }

    public LinearLayout getMessageOutLinearLayoutFr() {
        return messageOutLinearLayoutFr;
    }

    public LinearLayout getMessageOutLinearLayoutSe() {
        return messageOutLinearLayoutSe;
    }

    public LinearLayout getHeadLayout() {
        return headLayout;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
