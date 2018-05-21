package com.focustech.tobuy.ui.personcenter.component.message;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.ui.personcenter.activity.message.MessageActivity;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/18.
 */

public class SimpleMessageComponent extends BaseComponent {

    public static HashMap<LinearLayout, SimpleMessageComponent>  messages = new HashMap<>();
    public int userId = -1;

    private Context context;
    private HashMap<String, Integer> screenSize = EBApplication.screenSize;

    private LinearLayout outLinearLayout;
    private LinearLayout leftLinearLayout;
    private LinearLayout rightLinearLayout;

    private LinearLayout linearLayoutFr;
    private LinearLayout linearLayoutSe;

    private LinearLayout leftLinearLayoutFr;
    private LinearLayout rightLinearLayoutFr;

    private MyCircleImageView head;
    private MyTextView title;
    private MyTextView date;
    private MyTextView content;

    public SimpleMessageComponent(Context context){
        this.context = context;
        initLayout();
    }
    public SimpleMessageComponent(Context context, int userId) {
        this(context);
        this.userId = userId;
    }

    private void initLayout(){

        this.outLinearLayout = new LinearLayout(context);
        //设置监听
        outLinearLayout.setOnClickListener((View.OnClickListener)context);
        messages.put(outLinearLayout, this);

        this.leftLinearLayout = new LinearLayout(context);
        this.rightLinearLayout = new LinearLayout(context);
        this.linearLayoutFr = new LinearLayout(context);
        this.linearLayoutSe = new LinearLayout(context);
        this.leftLinearLayoutFr = new LinearLayout(context);
        this.rightLinearLayoutFr = new LinearLayout(context);

        outLinearLayout.setGravity(LinearLayout.HORIZONTAL);
        outLinearLayout.setLayoutParams(getLLP(MP, (int)(SCREEN_HEIGHT*0.1)));
        outLinearLayout.setPadding(5,5,5,5);

        leftLinearLayout.setLayoutParams(getLLP(0, MP, 0.2f));

        rightLinearLayout.setLayoutParams(getLLP(0, MP, 0.8f));
        rightLinearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayoutFr.setLayoutParams(getLLP(MP, MP));
        linearLayoutSe.setLayoutParams(getLLP(MP, MP));
        linearLayoutSe.setGravity(Gravity.CENTER | Gravity.LEFT);

        leftLinearLayoutFr.setLayoutParams(getLLP(MP, MP));
        leftLinearLayoutFr.setOrientation(LinearLayout.HORIZONTAL);
        leftLinearLayoutFr.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

        rightLinearLayoutFr.setLayoutParams(getLLP(MP, MP));
        rightLinearLayoutFr.setOrientation(LinearLayout.HORIZONTAL);
        rightLinearLayoutFr.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

        initComponent();
    }
    private void initComponent(){
        head = new MyCircleImageView(context);
        title = new MyTextView(context);
        date = new MyTextView(context);
        content = new MyTextView(context);

        head.setImageResource(R.drawable.neko);

        title.setText("系统消息");
        title.setIncludeFontPadding(false);
        title.setGravity(Gravity.LEFT);

        date.setText("2018-04-18");
        date.setIncludeFontPadding(false);
        date.setGravity(Gravity.RIGHT);

        content.setText("系统消息");
        content.setIncludeFontPadding(false);
        content.setGravity(Gravity.LEFT);
        content.setEllipsize(TextUtils.TruncateAt.END);

        assemble();
    }

    private void assemble(){
        outLinearLayout.addView(leftLinearLayout);
        outLinearLayout.addView(rightLinearLayout);

        rightLinearLayout.addView(linearLayoutFr);
        rightLinearLayout.addView(linearLayoutSe);

        linearLayoutFr.addView(leftLinearLayoutFr);
        linearLayoutFr.addView(rightLinearLayoutFr);

        leftLinearLayout.addView(head);
        leftLinearLayoutFr.addView(title);
        rightLinearLayoutFr.addView(date);
        linearLayoutSe.addView(content);
        initListeners();

    }

    private void initListeners(){
        this.outLinearLayout.setOnClickListener((View.OnClickListener)context);
    }

    public MyTextView getContent() {
        return content;
    }

    public MyTextView getDate() {
        return date;
    }

    public MyCircleImageView getHead() {
        return head;
    }

    public LinearLayout getOutLinearLayout() {
        return outLinearLayout;
    }

    public MyTextView getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
