package com.mlj.practicesrep.translucentTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.mlj.practicesrep.R;

//1.清单文件中设置theme
//android:theme="@android:style/Theme.Translucent.NoTitleBar"
//如果直接使用了，你的程序也许会直接崩溃！原因可能是你的Activity继承的是V7包的AppCompatActivity，这时你所使用的主题要与Theme.AppCompat兼容，否则程序就会崩溃了

//2方法二
/*1.res/values/styles.xml
<resources>
<style name="Transparent
        ">
<item name="android:windowBackground">@color/transparent_background</item>
<item name="android:windowNoTitle">true</item>
<item name="android:windowIsTranslucent">true</item>
<item name="android:windowAnimationStyle">@+android:style/Animation.Translucent</item>
</style>
</resources>*/

/*2 res/values/color.xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
<color name="transparent_background">#50000000</color>
</resources>*/

/*3.xml设置或者代码设置
<activity android:name=".TransparentActivity" android:theme="@style/Transparent">
</activity>
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Transparent);
        setContentView(R.layout.transparent);
        }*/

/*4.不错，确实透明了．但是问题又来了，layout里的button不透明啊．如果能让他们也透明或者半透明呢？那得设置窗口属性．
    Window window=getWindow();
    WindowManager.LayoutParams wl = window.getAttributes();
    wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
    wl.alpha=0.6f;    　　这句就是设置窗口里崆件的透明度的．０.０全透明．１.０不透明．
    window.setAttributes(wl);*/

/*5.实现dialog样式 继承已有的style，实现类似dialog的布局
    自定义样式继承@android:style/Theme.Dialog（推荐）
    <style name="myTransparent" parent="@android:style/Theme.Dialog">
    <item name="android:windowBackground">@color/gray_64</item>
    <item name="android:windowNoTitle">true</item>
    <item name="android:windowIsTranslucent">true</item>
    <item name="android:windowAnimationStyle">@android:style/Animation.Translucent</item>
    <item name="android:windowIsFloating">true</item>
    <!--<item name="android:windowContentOverlay">@null</item>-->
    <!--<item name="android:backgroundDimEnabled">true</item>-->
    </style>*/
public class TranslucentTopActivity extends Activity {

    // 长度最多25
    private static final String TAG = "TranslucTopActivity111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_translucent_top);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    // home回来都会执行
    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}

//不会走stop 生命周期变化为：
/*
// 进入页面1
        E/translucentTest111:onCreate()
        E/translucentTest111:onStart:
        E/translucentTest111:onResume:
// 打开透明页面
        E/translucentTest111: onPause:
// home一下
        E/translucentTest111: onStop:
// home回来
        E/translucentTest111: onRestart://
        E/translucentTest111: onStart:
// finish半透明页面
        E/translucentTest111: onResume:
*/
