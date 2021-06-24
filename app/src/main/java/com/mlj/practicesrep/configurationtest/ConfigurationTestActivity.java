package com.mlj.practicesrep.configurationtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.mlj.practicesrep.R;

public class ConfigurationTestActivity extends BaseConfigurationActivity {
    private static final String TAG = "docker11111";
    private Button mTestButton;
    private Button mJumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_test);
        mTestButton = findViewById(R.id.test_button);
        Log.d(TAG, "onCreate: ");
        ViewGroup.LayoutParams layoutParams = mTestButton.getLayoutParams();
        layoutParams.height = 200;
        layoutParams.width = 1500;
        mTestButton.setLayoutParams(layoutParams);
        //
        mJumpBtn = findViewById(R.id.jumpBtn);
        mJumpBtn.setOnClickListener(v -> {
            startActivity(new Intent(ConfigurationTestActivity.this, AnotherConfigurationActivity.class));
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}

/*

        “mcc“ 移动国家号码，由三位数字组成，每个国家都有自己独立的MCC，可以识别手机用户所属国家。
        “mnc“ 移动网号，在一个国家或者地区中，用于区分手机用户的服务商。
        “locale“ 所在地区发生变化。
        “touchscreen“ 触摸屏已经改变。（这不应该常发生。）
        “keyboard“ 键盘模式发生变化，例如：用户接入外部键盘输入。
        “keyboardHidden“ 用户打开手机硬件键盘
        “navigation“ 导航型发生了变化。（这不应该常发生。）
        “orientation“ 设备旋转，横向显示和竖向显示模式切换。
        “fontScale“ 全局字体大小缩放发生改变


        对android:configChanges属性，一般认为有以下几点：
        1、不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次

        2、设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次

        3、设置Activity的android:configChanges="orientation|keyboardHidden"时，切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法

        但是，自从Android 3.2（API 13），在设置Activity的android:configChanges="orientation|keyboardHidden"后，还是一样会重新调用各个生命周期的。因为screen size也开始跟着设备的横竖切换而改变。所以，在AndroidManifest.xml里设置的MiniSdkVersion和 TargetSdkVersion属性大于等于13的情况下，如果你想阻止程序在运行时重新加载Activity，除了设置"orientation"，你还必须设置"ScreenSize"。
        解决方法：
        AndroidManifest.xml中设置android:configChanges="orientation|screenSize“


          在Android系统默认的情况下，当“屏幕方向”或“键盘显示隐藏”变化时都会销毁当前Activity，创建新的Activity。如果不希望重新创建Activity实例，可以在AndroidManifest.xml中配置



        android:configChanges="keyboardHidden|orientation" >
        1
        这样就不会销毁重建了，在配置了这个属性后，android:configChanges 属性就会捕获“屏幕方向”和“键盘显示隐藏”变化，当捕获到这些变化后会调用Activity的onConfigurationChanged()方法。
        然而上面的配置只在android4.0之前的版本起作用，在android 4.0 以上不起作用，必须要加上screenSize,也就是说android 4.0以后的版本必须这样配置

        android:configChanges="keyboardHidden|orientation|screenSize"*/
