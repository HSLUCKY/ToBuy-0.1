package com.focustech.tobuy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;


/**
 * 进行GIF图像的绘制
 */
public class MyWelcomGifView extends ImageView {


    +
        private Movie movie;
    private long movieStart;

    public MyWelcomGifView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        movie = Movie.decodeStream(getResources().openRawResource(R.drawable.xihuan));
    }


    @Override
    protected void onDraw(Canvas canvas){
        long curTime = android.os.SystemClock.uptimeMillis();

        if (movieStart == 0){
            movieStart = curTime;
        }
        if (movie != null){
            int duration = movie.duration();
            if (duration == 0){
                duration = 1000;
            }
            int relTime = (int) ((curTime - movieStart) % duration);
            movie.setTime(relTime);
            movie.draw(canvas, EBApplication.screenSize.get("SCREEN_WIDTH") * 0.35f,
                    EBApplication.screenSize.get("SCREEN_HEIGHT")*0.32f);
            invalidate();
        }

        super.onDraw(canvas);
    }
}
