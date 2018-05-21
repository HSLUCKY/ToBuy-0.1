package com.focustech.tobuy.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;

/**
 * Created by Administrator on 2018/4/29.
 */

public class MyGenderCheckBoxGroupView extends BaseComponent {
    private GenderCheckBox[] genders = new GenderCheckBox[3];
    private Context context;
    public int selected;
    public int count = 0;

    public MyGenderCheckBoxGroupView(Context context){
        this.context = context;
        initComponent();
    }

    public void initComponent(){
        genders[0] = new GenderCheckBox();
        genders[0].leftPic.setImageResource(R.drawable.message);
        genders[0].rightTx.setText("男");
        genders[0].rightTx.setTextSize(18);

        genders[1] = new GenderCheckBox();
        genders[1].leftPic.setImageResource(R.drawable.message);
        genders[1].rightTx.setText("女");
        genders[1].rightTx.setTextSize(18);

        genders[2] = new GenderCheckBox();
        genders[2].leftPic.setImageResource(R.drawable.message);
        genders[2].rightTx.setText("你猜");
        genders[2].rightTx.setTextSize(18);
    }

    public class GenderCheckBox{
        public boolean status;
        public int id;

        public LinearLayout outLinearLayout;
        public LinearLayout leftLinearLayout;
        public LinearLayout rightLinearLayout;
        public ImageView leftPic;
        public TextView rightTx;

        public GenderCheckBox(){
            outLinearLayout = new LinearLayout(context);
            leftLinearLayout = new LinearLayout(context);
            rightLinearLayout = new LinearLayout(context);
            leftPic = new ImageView(context);
            rightTx = new TextView(context);
            MyGenderCheckBoxGroupView.this.count++;
            GenderCheckBox.this.id = MyGenderCheckBoxGroupView.this.count-1;
            initView();
        }

        private void initView(){
            LinearLayout.LayoutParams outLayoutParams = getLLP(MP, MP);
            outLayoutParams.gravity = Gravity.CENTER;
            outLinearLayout.setLayoutParams(outLayoutParams);
            outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            leftLinearLayout.setLayoutParams(getLLP(MP,MP));
            leftLinearLayout.setGravity(Gravity.RIGHT|Gravity.CENTER);
            rightLinearLayout.setLayoutParams(getLLP(MP,MP));
            rightLinearLayout.setGravity(Gravity.LEFT|Gravity.CENTER);

            leftPic.setLayoutParams(getLLP(WP,WP));
            rightTx.setLayoutParams(getLLP(WP,WP));
            assemble();
        }
        private void assemble(){
            outLinearLayout.addView(leftLinearLayout);
            outLinearLayout.addView(rightLinearLayout);
            leftLinearLayout.addView(leftPic);
            rightLinearLayout.addView(rightTx);
            initListener();
        }
        private void initListener(){
            this.outLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selected == id){
                        return;
                    }
                    status = !status;
                    if (status){
                        MyGenderCheckBoxGroupView.this.selected = GenderCheckBox.this.id;
                        GenderCheckBox.this.outLinearLayout.setBackgroundColor(0xFFFF9999);
                        clear(id);
                    }else {
                        GenderCheckBox.this.outLinearLayout.setBackgroundColor(Color.WHITE);
                    }
                }
            });
        }

        private void clear(int id){
            for (int i = 0; i < genders.length; i++){
                if (genders[i].id != id){
                    genders[i].status = false;
                    genders[i].outLinearLayout.setBackgroundColor(Color.WHITE);
                }
            }
        }

    }

    public void setSex(int id){
        this.genders[id].status = !this.genders[id].status;
        this.genders[id].outLinearLayout.setBackgroundColor(0xFFFF9999);
        this.genders[id].clear(id);
    }
    public int getSelectedId(){
        for (int i = 0; i < this.getGenders().length; i++){
            if (this.getGenders()[i].status == true){
                return this.getGenders()[i].id;
            }
        }
        return -1;
    }

    public GenderCheckBox[] getGenders() {
        return genders;
    }
}
