package com.focustech.tobuy.ui.personcenter.component.personal;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/29.
 */

public class SimpleHistoryComponent extends BaseComponent {

    private Context context;
    public static HashMap<LinearLayout, SimpleCardHistory> histories = new HashMap<>();

    public SimpleHistoryComponent(Context context) {
        this.context = context;
    }

    public class SimpleCardHistory {

        public int cardId = -1;

        private LinearLayout outLinearLayout;
        private LinearLayout leftLinearLayout;
        private LinearLayout rightLinearLayout;
        private LinearLayout leftLinearLayout1;
        private LinearLayout leftLinearLayout2;

        private MyTextView title;
        private MyTextView content;
        private MyTextView historyDate;

        public SimpleCardHistory() {
            outLinearLayout = new LinearLayout(context);
            leftLinearLayout = new LinearLayout(context);
            rightLinearLayout = new LinearLayout(context);
            leftLinearLayout1 = new LinearLayout(context);
            leftLinearLayout2 = new LinearLayout(context);

            title = new MyTextView(context);
            content = new MyTextView(context);
            historyDate = new MyTextView(context);
            initLayout();
        }

        private void initLayout() {
            outLinearLayout.setLayoutParams(getLLP(MP, WP));
            outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            outLinearLayout.setOnClickListener((View.OnClickListener)context);
            outLinearLayout.setPadding(0, 10, 0, 0);
            histories.put(outLinearLayout, this);
            leftLinearLayout.setLayoutParams(getLLP(0, WP, 0.8f));
            leftLinearLayout.setOrientation(LinearLayout.VERTICAL);
            leftLinearLayout.setPadding(20, 0, 0, 0);
            rightLinearLayout.setLayoutParams(getLLP(0, MP, 0.2f));
            rightLinearLayout.setGravity(Gravity.CENTER);
            leftLinearLayout1.setLayoutParams(getLLP(MP, WP));
            leftLinearLayout1.setGravity(Gravity.CENTER | Gravity.LEFT);
            leftLinearLayout1.setPadding(0, 0, 0, 20);
            leftLinearLayout2.setLayoutParams(getLLP(MP, WP));
            leftLinearLayout2.setGravity(Gravity.CENTER | Gravity.LEFT);

            initComponent();
        }

        private void initComponent() {
            title.setText("帖子标题");
            title.setTextSize(18);
            title.setGravity(Gravity.LEFT);
            title.setMaxLines(1);
            title.setEllipsize(TextUtils.TruncateAt.END);

            content.setText("帖子内容");
            content.setGravity(Gravity.LEFT);
            content.setMaxLines(1);
            content.setEllipsize(TextUtils.TruncateAt.END);

            historyDate.setText("5天前");
            assemble();
        }

        private void assemble() {
            outLinearLayout.addView(leftLinearLayout);
            outLinearLayout.addView(rightLinearLayout);
            leftLinearLayout.addView(leftLinearLayout1);
            leftLinearLayout.addView(leftLinearLayout2);
            rightLinearLayout.addView(historyDate);
            leftLinearLayout1.addView(title);
            leftLinearLayout2.addView(content);
        }

        public MyTextView getHistoryDate() {
            return historyDate;
        }

        public int getCardId() {
            return cardId;
        }

        public LinearLayout getLeftLinearLayout1() {
            return leftLinearLayout1;
        }

        public LinearLayout getLeftLinearLayout2() {
            return leftLinearLayout2;
        }

        public LinearLayout getLeftLinearLayout() {
            return leftLinearLayout;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getRightLinearLayout() {
            return rightLinearLayout;
        }

        public MyTextView getTitle() {
            return title;
        }
    }

    public SimpleCardHistory getSimpleCardHistory(){
        return new SimpleCardHistory();
    }

}
