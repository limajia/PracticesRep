package com.mlj.practicesrep.floatwindowtest;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

//在 Android 6.0（API 级别 23）之前，开发者可以直接在应用中使用悬浮窗功能，
// 但在 Android 6.0 之后，为了保证用户的隐私和安全，Google 限制了应用程序对悬浮窗的访问权限。

//1.申请 SYSTEM_ALERT_WINDOW 权限
//要使用全局悬浮窗，您需要在 AndroidManifest.xml 文件中声明 SYSTEM_ALERT_WINDOW 权限.可以不申请权限直接跳转setting页面去处理(其他权限也可以这样处理，这个必须这样处理)
//2.
/*
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
        // TODO: 实现全局悬浮窗逻辑
        }
*/
//3.
/*
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
        // TODO: 实现全局悬浮窗逻辑
        } else {
        // TODO: 处理用户未授予权限的情况
        }
        }
        }
*/

//4.
/*
private void showFloatingView() {
        // 创建 WindowManager
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 创建悬浮窗视图
        FloatingView floatingView = new FloatingView(this);

        //在Android中，要指定一个Activity显示在系统页面前面，您需要设置Activity的Window类型为TYPE_APPLICATION_OVERLAY或TYPE_SYSTEM_ALERT
        // 创建 LayoutParams 并设置相关属性
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?//这里
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY ://这里
        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
        PixelFormat.TRANSLUCENT);

        // 将自定义视图添加到 WindowManager 中
        windowManager.addView(floatingView, layoutParams);
        }
*/


public class FloatWidowTestActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 11;
    private Button jumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_widow_test);

        initView();
    }

    private void initView() {
        jumpBtn = findViewById(R.id.jumpBtn);
        jumpBtn.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(FloatWidowTestActivity.this, FloatPermissionGuideActivity.class));
                    }
                }, 500);
            } else {
                //有权限
                showWindow();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                Toast.makeText(getApplicationContext(), "haspr", Toast.LENGTH_SHORT).show();
                showWindow();
            } else {
                Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showWindow() {
        // 创建 WindowManager
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        // 创建 LayoutParams 并设置相关属性
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        // 将自定义视图添加到 WindowManager 中
        windowManager.addView(LayoutInflater.from(FloatWidowTestActivity.this).inflate(R.layout.activity_float_permission_guide,null), layoutParams);
    }
}