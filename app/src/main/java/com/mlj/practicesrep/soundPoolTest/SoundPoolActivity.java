package com.mlj.practicesrep.soundPoolTest;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

import java.io.IOException;

public class SoundPoolActivity extends AppCompatActivity {

    private SoundPool soundPool1;
    private SoundPool soundPool2;
    private SoundPool soundPool3;
    private Button playBtn;
    private Button playBtnOnceLoad;
    private Button playBtnOnceLoadPlay;
    private Button audioRequestFocus1;
    private Button audioRequestFocus2;
    private int soundPool2SampleId;
    private int soundPool3SampleId;
    private Button audioRequestFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);
        initView();
    }

    private void createSoundPool1() {
        if (soundPool1 != null) return;
        soundPool1 = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()).build();

        soundPool1.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            System.out.println("docker11=onLoadComplete】sampleId= " + sampleId + " status=" + status); //status : 0 = success
            this.soundPool1.play(sampleId, 1, 1, 0, 0, 1);
        });
    }

    private void createSoundPool2() {
        if (soundPool2 != null) return;
        soundPool2 = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()).build();

        soundPool2.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            System.out.println("docker11=onLoadComplete】sampleId= " + sampleId + " status=" + status); //status : 0 = success
            soundPool2SampleId = sampleId;
        });
    }

    private void createSoundPool3() {
        if (soundPool3 != null) return;
        soundPool3 = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()).build();

        soundPool3.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            System.out.println("docker11=onLoadComplete】sampleId= " + sampleId + " status=" + status); //status : 0 = success
            soundPool3SampleId = sampleId;
        });
        try {
            soundPool3.load(getApplication().getAssets().openFd("quiz/answer_bg_audio.mp3"), 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 创建音频焦点监听器
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // 成功获取音频焦点
                // 在此处处理获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:onAudioFocusChange= AUDIOFOCUS_GAIN");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // 失去音频焦点长时间无法恢复
                // 在此处处理失去焦点后的操作，例如停止或释放音频资源
                System.out.println("docker22:onAudioFocusChange= AUDIOFOCUS_LOSS");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // 暂时失去音频焦点，但预计会很快恢复
                // 在此处处理暂时失去焦点后的操作，例如暂停音频播放
                System.out.println("docker22:onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // 暂时失去音频焦点，但可以继续以降低音量的方式播放音频
                // 在此处处理暂时失去焦点后的操作，例如降低音频播放的音量
                System.out.println("docker22:onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            }
        }
    };

    // 创建音频焦点监听器
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener1 = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // 成功获取音频焦点
                // 在此处处理获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:1onAudioFocusChange= AUDIOFOCUS_GAIN");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // 失去音频焦点长时间无法恢复
                // 在此处处理失去焦点后的操作，例如停止或释放音频资源
                System.out.println("docker22:1onAudioFocusChange= AUDIOFOCUS_LOSS");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // 暂时失去音频焦点，但预计会很快恢复
                // 在此处处理暂时失去焦点后的操作，例如暂停音频播放
                System.out.println("docker22:1onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // 暂时失去音频焦点，但可以继续以降低音量的方式播放音频
                // 在此处处理暂时失去焦点后的操作，例如降低音频播放的音量
                System.out.println("docker22:1onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            }
        }
    };

    // 创建音频焦点监听器
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener2 = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // 成功获取音频焦点
                // 在此处处理获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:2onAudioFocusChange= AUDIOFOCUS_GAIN");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // 失去音频焦点长时间无法恢复
                // 在此处处理失去焦点后的操作，例如停止或释放音频资源
                System.out.println("docker22:2onAudioFocusChange= AUDIOFOCUS_LOSS");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                // 暂时失去音频焦点，但预计会很快恢复
                // 在此处处理暂时失去焦点后的操作，例如暂停音频播放
                System.out.println("docker22:2onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // 暂时失去音频焦点，但可以继续以降低音量的方式播放音频
                // 在此处处理暂时失去焦点后的操作，例如降低音频播放的音量
                System.out.println("docker22:2onAudioFocusChange= AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
            }
        }
    };

    private void initView() {
        playBtn = findViewById(R.id.play_btn_per_load);
        playBtn.setOnClickListener(v -> {
            createSoundPool1();
            //先load后播放
            //quiz/answer_bg_audio.mp3
            try {
                int immediateID = soundPool1.load(getApplication().getAssets().openFd("quiz/answer_bg_audio.mp3"), 1);
                //立即返回一个id，在completeListener回调中同时也返回回来
                System.out.println("docker11=soundPool.load immediaID = " + immediateID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //输出日志为：相差不到一秒，但是也得等待load完成然后回调回来。
            //2023-06-26 20:45:54.669 31365-31365/com.mlj.practicesrep I/System.out: docker11=soundPool.load immediaID = 1
            //2023-06-26 20:45:55.021 31365-31365/com.mlj.practicesrep I/System.out: docker11=onLoadComplete】sampleId= 1 status=0
        });
        //!!!每次load 都会产生一个新的sound ID，结果就是多次load，播放，会有多个声音一起播放。
        //正确用法是：load一次，然后播放多次，播放的时候传入load的id
        //
        //使用 `SoundPool.load()` 方法重复加载音频文件不会直接导致内存增加。但是也是到回调回来也是耗时的。
        //当你调用 `SoundPool.load()` 方法加载音频文件时，`SoundPool` 会将音频数据加载到内存中，并为其分配内存空间。
        //如果你多次调用 `load()` 方法加载同一音频文件，`SoundPool` 会检测到该文件已经加载过，不会重复分配额外的内存空间。
        //相反，它会返回一个音频文件的标识符，该标识符用于后续的播放和管理操作。
        //所以，即使你多次调用 `load()` 方法加载同一音频文件，内存使用量不会增加，因为实际的音频数据只会在内存中保存一份。
        //然而，如果你调用 `load()` 方法加载多个不同的音频文件，每个文件都会分配相应的内存空间。因此，如果你加载了大量的不同音频文件，可能会占用较多的内存。
        //在这种情况下，可以考虑使用适当的资源管理和内存释放策略，以确保在不需要时及时释放音频文件的内存。


        playBtnOnceLoad = (Button) findViewById(R.id.play_btn_once_load);
        playBtnOnceLoad.setOnClickListener(v -> {
            createSoundPool2();
            try {
                soundPool2.load(getApplication().getAssets().openFd("quiz/answer_bg_audio.mp3"), 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        playBtnOnceLoadPlay = (Button) findViewById(R.id.play_btn_once_load_play);
        playBtnOnceLoadPlay.setOnClickListener(v -> {
            //播放同一个音乐
            this.soundPool2.play(soundPool2SampleId, 1, 1, 0, 0, 1);
            System.out.println("docker11: playBtnOnceLoadPlay.setOnClickListener soundPool2SampleId = " + soundPool2SampleId);
            //2023-06-26 21:09:51.633 8573-8573/com.mlj.practicesrep I/System.out: docker11=onLoadComplete】sampleId= 1 status=0
            //2023-06-26 21:09:52.997 8573-8573/com.mlj.practicesrep I/System.out: docker11: playBtnOnceLoadPlay.setOnClickListener soundPool2SampleId = 1
            //2023-06-26 21:09:59.849 8573-8573/com.mlj.practicesrep I/System.out: docker11: playBtnOnceLoadPlay.setOnClickListener soundPool2SampleId = 1
            //重复播放同一个soundPool2SampleId = 1的音乐，也会多个声音一起播放。重叠在一起
        });


        audioRequestFocus = (Button) findViewById(R.id.audio_request_focus);
        audioRequestFocus.setOnClickListener(v -> {
            AudioManager systemService = (AudioManager) (getApplication().getSystemService(Context.AUDIO_SERVICE));
            int result = systemService.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            // 检查是否成功获取焦点
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // 成功获取焦点
                // 在此处执行获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:result=成功申请焦点");
            } else {
                // 无法获取焦点
                // 在此处处理无法获取焦点的情况，例如提示用户或采取其他措施
                System.out.println("docker22:result=无法获取焦点");
            }
        });


        Button releaseFocus = (Button) findViewById(R.id.audio_release_focus);
        releaseFocus.setOnClickListener(v -> {
            AudioManager systemService = (AudioManager) (getApplication().getSystemService(Context.AUDIO_SERVICE));
            int result = systemService.abandonAudioFocus(audioFocusChangeListener);
            // 检查是否成功获取焦点
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                System.out.println("docker22:result=成功释放焦点");
            } else {
                System.out.println("docker22:result=无法释放焦点");
            }
        });
        audioRequestFocus1 = (Button) findViewById(R.id.audio_request_focus1);
        audioRequestFocus1.setOnClickListener(v -> {
            createSoundPool3();
            this.soundPool3.play(soundPool3SampleId, 1, 1, 0, -1, 1.5f);
            AudioManager systemService = (AudioManager) (getApplication().getSystemService(Context.AUDIO_SERVICE));
            int result = systemService.requestAudioFocus(audioFocusChangeListener1, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            // 检查是否成功获取焦点
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // 成功获取焦点
                // 在此处执行获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:1result=成功申请焦点");
            } else {
                // 无法获取焦点
                // 在此处处理无法获取焦点的情况，例如提示用户或采取其他措施
                System.out.println("docker22:1result=无法获取焦点");
            }
        });
        audioRequestFocus2 = (Button) findViewById(R.id.audio_request_focus2);
        audioRequestFocus2.setOnClickListener(v -> {
            createSoundPool3();
            this.soundPool3.play(soundPool3SampleId, 1, 1, 0, -1, 0.5f);
            AudioManager systemService = (AudioManager) (getApplication().getSystemService(Context.AUDIO_SERVICE));
            int result = systemService.requestAudioFocus(audioFocusChangeListener2, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            // 检查是否成功获取焦点
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // 成功获取焦点
                // 在此处执行获取焦点后的操作，例如开始播放音频
                System.out.println("docker22:2result=成功申请焦点");
            } else {
                // 无法获取焦点
                // 在此处处理无法获取焦点的情况，例如提示用户或采取其他措施
                System.out.println("docker22:2result=无法获取焦点");
            }
        });
    }
    //结论焦点是互斥的。
    //只有最近设置的那个FocusChangeListener会收到requestAudioFocus触发的回调。如：onAudioFocusChange= AUDIOFOCUS_LOSS
    //且只有最近设置的那个FocusChangeListener会收到abandonAudioFocus触发的回调。如：onAudioFocusChange= AUDIOFOCUS_GAIN

    //！！！这是个关键配置
    //setMaxStreams(5) 播放同一个id会重叠播放，因为这里设置的是可以5个同时播放
    //setMaxStreams(1) 播放同一个id会替换掉之前的音乐，重新播放
}