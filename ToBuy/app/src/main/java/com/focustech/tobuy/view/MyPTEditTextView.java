package com.focustech.tobuy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;

import com.focustech.tobuy.R;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyPTEditTextView extends EditText {

    public MyPTEditTextView(Context context) {
        super(context);
    }

    public MyPTEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void insertDrawable(int id, String src){

        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), id);

        //配置SpannableString
        SpannableString spannableString = new SpannableString(src);
        Drawable drawable = new BitmapDrawable(this.getContext().getResources(), bitmap);
        drawable.setBounds(0,0, bitmap.getWidth(), bitmap.getHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);
        spannableString.setSpan(imageSpan, 0, src.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        this.getText().insert(this.getSelectionStart(), spannableString);

    }

    public void insertDrawable(int id, int index){
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), id);
    }

}
