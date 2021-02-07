package com.mlj.practicesrep.player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

import java.io.IOException;

public class playerActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mSurfaceView = findViewById(R.id.surface_view);
        View testButton = findViewById(R.id.mmmmmmmmmmmm);
        //布局
        View changeSizeBtn = findViewById(R.id.change_size_btn);
        // 这种建立局部变量的方式 可以学习
        // TODO: 2021/2/7
        final int[] offset = {0};
        changeSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset[0] += 20;
                ViewGroup.LayoutParams layoutParams = mSurfaceView.getLayoutParams();
                layoutParams.width = 400 + offset[0];
                layoutParams.height = 200 + offset[0];
                mSurfaceView.setLayoutParams(layoutParams);
            }
        });
        //scale
        View sclebtn = findViewById(R.id.scale_btn);
        final float[] offset1 = {1};
        sclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset1[0] += 0.1;
                mSurfaceView.setScaleX(offset1[0]);
                mSurfaceView.setScaleY(offset1[0]);

                testButton.setScaleX(offset1[0]);
                testButton.setScaleY(offset1[0]);
            }
        });
        //alpha 是无效
        View alphabtn = findViewById(R.id.alpha_btn);
        final float[] offset2 = {1};
        alphabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset2[0] -= 0.1;
//                mSurfaceView.setAlpha(offset2[0]);
                mSurfaceView.animate().translationYBy(-10).setDuration(1000).start(); // 这个是有效的
                testButton.animate().translationYBy(-10).setDuration(1000).start(); // 这个是有效的
            }
        });
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                // 创建MediaPlayer调用的是create方法，第一次启动播放前 不需要再调用prepare()，如果是使用构造方法构造的
                // 话，则需要调用一次prepare()方法

                // MediaPlayer mediaPlayer = new MediaPlayer();
                //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); 音频加类型
                mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDisplay(holder);
                    mMediaPlayer.setDataSource("https://v-cdn.zjol.com.cn/280443.mp4");
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mMediaPlayer.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.reset();
        mMediaPlayer.release();
    }
}