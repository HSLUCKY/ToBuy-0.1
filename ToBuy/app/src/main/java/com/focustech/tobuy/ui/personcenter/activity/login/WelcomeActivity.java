package com.focustech.tobuy.ui.personcenter.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.personcenter.activity.login.LoginActivity;

import java.util.HashMap;

/**
 * 开机动画以及音效
 */

public class WelcomeActivity extends Activity{

    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    HashMap<Integer, Integer> soundPoolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 定时跳至登录页面
         */
        final Intent localIntent = new Intent(this, LoginActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(localIntent);
            }
        }, 3000);
        /**
         * 设置app开启时的动画
         */
        this.initSoulds();
        this.playSound(1, 0);
        this.mediaPlayer.start();

        setContentView(R.layout.welcom_activity);
    }

    /**
     * 声音初始化
      */
    private void initSoulds(){
        //初始化音效资源
        mediaPlayer = MediaPlayer.create(this, R.raw.d);
        //设置音乐数量， 音乐类型， 音乐质量
        soundPool = new SoundPool(1, AudioManager.STREAM_RING, 100);

        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, soundPool.load(this, R.raw.d, 1));
    }

    /**
     * 播放音乐
     * @param sound 音乐次序
     * @param loop 是否循环
     */
    private void playSound(int sound, int loop){
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        //获取当前音量
        float currenVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //获取系统最大音量
        float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //获取当前音量百分比
        float volume = currenVolume / maxVolume;

        //声音ID， 左声道音量， 右声道音量， 流优先级（最低0）， 是否循环， 播放速度（1.0正常播放，   0.5 - 2.0），
        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }

}
