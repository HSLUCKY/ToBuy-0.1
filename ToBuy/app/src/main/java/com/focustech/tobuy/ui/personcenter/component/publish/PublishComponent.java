package com.focustech.tobuy.ui.personcenter.component.publish;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.ui.personcenter.component.community.SessionComponent;
import com.focustech.tobuy.view.MyCheckBoxView;
import com.focustech.tobuy.view.MyPTEditTextView;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Publish页面组件类
 */

public class PublishComponent extends BaseComponent {

    public static HashMap<LinearLayout, Integer> sessions = new HashMap<>();

    private Context context;

    public PublishComponent(Context context) {
        this.context = context;
    }


    public class EditLayout {
        private LinearLayout outLinearLayout;
        private LinearLayout editLinearLayout;
        private LinearLayout photoLinearLayout;

        private MyPTEditTextView ptEditText;
        private ImageView photoPic;

        public EditLayout() {
            this.outLinearLayout = new LinearLayout(context);
            this.editLinearLayout = new LinearLayout(context);
            this.photoLinearLayout = new LinearLayout(context);
            this.ptEditText = new MyPTEditTextView(context);
            this.photoPic = new ImageView(context);

            initLayout();
        }

        public void initLayout() {
            outLinearLayout.setLayoutParams(getLLP(MP, WP));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);
            outLinearLayout.setPadding(5, 5, 5, 5);
            editLinearLayout.setLayoutParams(getLLP(MP, 0, 0.6f));
            editLinearLayout.setOrientation(LinearLayout.VERTICAL);
            photoLinearLayout.setLayoutParams(getLLP(WP, 0, 0.1f));
            photoLinearLayout.setGravity(Gravity.LEFT);
            initComponent();
        }

        public void initComponent() {
            ptEditText.setLayoutParams(getLLP(MP, MP));
            ptEditText.setGravity(Gravity.START);
            ptEditText.setHint("内容\n\n\n\n\n\n\n\n\n");
            photoPic.setLayoutParams(getLLP(WP, WP));
            photoPic.setImageResource(R.drawable.hometip);
            assemble();
        }

        public void assemble() {
            outLinearLayout.addView(editLinearLayout);
            outLinearLayout.addView(photoLinearLayout);
            editLinearLayout.addView(ptEditText);
            photoLinearLayout.addView(photoPic);
        }

        public LinearLayout getEditLinearLayout() {
            return editLinearLayout;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getPhotoLinearLayout() {
            return photoLinearLayout;
        }

        public ImageView getPhotoPic() {
            return photoPic;
        }

        public MyPTEditTextView getPtEditText() {
            return ptEditText;
        }
    }

    public class TitleLayout {
        private LinearLayout outLinearLayout;

        private LinearLayout partLinearLayout;
        private LinearLayout[] partLinearLayouts;
        private TextView[] parts;

        private LinearLayout titleLinearLayout;
        private MyTextView ruleTx;

        public TitleLayout() {
            outLinearLayout = new LinearLayout(context);
            partLinearLayout = new LinearLayout(context);
            this.titleLinearLayout = new LinearLayout(context);
            this.ruleTx = new MyTextView(context);
            partLinearLayouts = new LinearLayout[4];
            parts = new TextView[4];
            for (int i = 0; i < parts.length; i++) {
                partLinearLayouts[i] = new LinearLayout(context);
                parts[i] = new TextView(context);
            }
            initLayout();
        }

        public void initLayout() {
            outLinearLayout.setLayoutParams(getLLP(MP, 200));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);

            partLinearLayout.setLayoutParams(getLLP(MP, MP));
            partLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int i = 0; i < parts.length; i++) {
                partLinearLayouts[i].setLayoutParams(getLLP(MP, MP));
                partLinearLayouts[i].setGravity(Gravity.CENTER);
                //===================================================
                partLinearLayouts[i].setOnClickListener((View.OnClickListener)context);
                sessions.put(partLinearLayouts[i], i);
                //===================================================
                parts[i].setTextSize(18);
                parts[i].setLayoutParams(getLLP(WP,WP));
                parts[i].setGravity(Gravity.CENTER);
                partLinearLayouts[i].addView(parts[i]);
                partLinearLayout.addView(partLinearLayouts[i]);
            }
            partLinearLayouts[0].setBackgroundColor(0xFFFF9999);
            parts[0].setText("购物");
            parts[1].setText("租借");
            parts[2].setText("帮帮");
            parts[3].setText("社区");

            titleLinearLayout.setLayoutParams(getLLP(MP, MP));
            titleLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            titleLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER);

            initComponent();
        }

        public void initComponent() {
            ruleTx.setGravity(Gravity.CENTER);
            ruleTx.setText("《言论管理规则》");
            assemble();
        }

        public void assemble() {
            outLinearLayout.addView(partLinearLayout);
            outLinearLayout.addView(titleLinearLayout);
            titleLinearLayout.addView(ruleTx);
        }

        public MyTextView getRuleTx() {
            return ruleTx;
        }

        public LinearLayout getOutLinearLayout() {
            return outLinearLayout;
        }

        public LinearLayout getPartLinearLayout() {
            return partLinearLayout;
        }

        public LinearLayout[] getPartLinearLayouts() {
            return partLinearLayouts;
        }

        public TextView[] getParts() {
            return parts;
        }

        public LinearLayout getTitleLinearLayout() {
            return titleLinearLayout;
        }
    }

    public class CheckBoxLine {
        private LinearLayout outLinearLayout;
        private LinearLayout leftLinearLayout;
        private LinearLayout rightLinearLayout;

        public CheckBoxLine() {
            this.outLinearLayout = new LinearLayout(context);
            this.leftLinearLayout = new LinearLayout(context);
            this.rightLinearLayout = new LinearLayout(context);
            initLayout();
        }

        public void initLayout() {
            outLinearLayout.setLayoutParams(getLLP(MP, 150));
            outLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            outLinearLayout.setGravity(Gravity.CENTER);
            leftLinearLayout.setLayoutParams(getLLP(MP, MP));
            leftLinearLayout.setGravity(Gravity.CENTER);
            rightLinearLayout.setLayoutParams(getLLP(MP, MP));
            rightLinearLayout.setGravity(Gravity.CENTER);

            assemble();
        }

        public void assemble() {
            outLinearLayout.addView(leftLinearLayout);
            outLinearLayout.addView(rightLinearLayout);
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
    }

    public EditLayout getEditLayout() {
        return new EditLayout();
    }

    public TitleLayout getTitleLayout() {
        return new TitleLayout();
    }

    public CheckBoxLine getCheckBoxLine() {
        return new CheckBoxLine();
    }

    public MyCheckBoxView getMyCheckBoxView() {
        return new MyCheckBoxView(context);
    }

}
