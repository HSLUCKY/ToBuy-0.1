package com.focustech.tobuy.ui.personcenter.component.personal;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.view.MyPTEditTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/30.
 */

public class SaleGoodsComponent extends BaseComponent {

    private Context context;

    public SaleGoodsComponent(Context context) {
        this.context = context;
    }

    public class SaleGoods{

        private LinearLayout outLinearLayout;
        private LinearLayout editLinearLayout;
        private LinearLayout priceLinearLayout;
        private LinearLayout tipLinearLayout;
        private LinearLayout photoLinearLayout;

        private MyPTEditTextView ptEditText;
        private EditText priceEditText;
        private EditText tipEditText;
        private ImageView photoPic;

        public SaleGoods(){
            this.outLinearLayout = new LinearLayout(context);
            this.editLinearLayout = new LinearLayout(context);
            this.priceLinearLayout = new LinearLayout(context);
            tipLinearLayout = new LinearLayout(context);
            this.photoLinearLayout = new LinearLayout(context);
            this.ptEditText = new MyPTEditTextView(context);
            this.priceEditText = new EditText(context);
            this.tipEditText = new EditText(context);
            this.photoPic = new ImageView(context);

            initLayout();
        }
        private void  initLayout(){
            outLinearLayout.setLayoutParams(getLLP(MP, WP));
            outLinearLayout.setOrientation(LinearLayout.VERTICAL);
            outLinearLayout.setPadding(5, 5, 5, 5);
            editLinearLayout.setLayoutParams(getLLP(MP, 0, 0.8f));
            editLinearLayout.setOrientation(LinearLayout.VERTICAL);
            priceLinearLayout.setLayoutParams(getLLP(MP, 0, 0.1f));
            priceLinearLayout.setGravity(Gravity.CENTER|Gravity.LEFT);
            tipLinearLayout.setLayoutParams(getLLP(MP, 0, 0.1f));
            tipLinearLayout.setGravity(Gravity.CENTER|Gravity.LEFT);
            photoLinearLayout.setLayoutParams(getLLP(WP, 0, 0.1f));
            photoLinearLayout.setGravity(Gravity.LEFT);
            initComponent();
        }
        private void initComponent(){
            ptEditText.setLayoutParams(getLLP(MP, MP));
            ptEditText.setGravity(Gravity.START);
            ptEditText.setHint("内容...\n\n\n\n\n\n\n\n\n");
            priceEditText.setLayoutParams(getLLP(MP, WP));
            priceEditText.setGravity(Gravity.START);
            priceEditText.setHint("价格...");
            priceEditText.setMaxLines(1);
            priceEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
           /* priceEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20), new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    Pattern pattern = Pattern.compile("[\\d]{1,10}[.]{0,1}[\\d]{1,}");
                    Matcher matcher = pattern.matcher(source);
                    return matcher.matches() ? source : "";
                }
            }});*/
            tipEditText.setLayoutParams(getLLP(MP, WP));
            tipEditText.setGravity(Gravity.START);
            tipEditText.setHint("卖家提示...");
            photoPic.setLayoutParams(getLLP(WP, WP));
            photoPic.setImageResource(R.drawable.neko);
            assemble();
        }
        private void assemble(){
            outLinearLayout.addView(editLinearLayout);
            outLinearLayout.addView(priceLinearLayout);
            outLinearLayout.addView(tipLinearLayout);
            outLinearLayout.addView(photoLinearLayout);

            editLinearLayout.addView(ptEditText);
            priceLinearLayout.addView(priceEditText);
            tipLinearLayout.addView(tipEditText);
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

        public EditText getPriceEditText() {
            return priceEditText;
        }

        public LinearLayout getPriceLinearLayout() {
            return priceLinearLayout;
        }

        public MyPTEditTextView getPtEditText() {
            return ptEditText;
        }

        public EditText getTipEditText() {
            return tipEditText;
        }

        public LinearLayout getTipLinearLayout() {
            return tipLinearLayout;
        }
    }

    public SaleGoods getSaleGoods(){
        return new SaleGoods();
    }
}
