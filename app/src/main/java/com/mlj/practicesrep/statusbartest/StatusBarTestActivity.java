package com.mlj.practicesrep.statusbartest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

/**
 * @author
 * https://juejin.cn/post/7203563038301061181#heading-4 Android 状态栏的正确使用方式
 */
public class StatusBarTestActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtnLightStatus;
    private Button mBtnWhiteStatus;
    private Button mBtnStatusColor;
    private Button mBtnShowBelowStatus;
    private Button mBtnHideStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_test);
        initView();
    }

    private void initView() {
        mBtnLightStatus = (Button) findViewById(R.id.btn_light_status);
        mBtnLightStatus.setOnClickListener(this);
        mBtnWhiteStatus = (Button) findViewById(R.id.btn_white_status);
        mBtnWhiteStatus.setOnClickListener(this);
        mBtnStatusColor = (Button) findViewById(R.id.btn_status_color);
        mBtnStatusColor.setOnClickListener(this);
        mBtnShowBelowStatus = (Button) findViewById(R.id.btn_show_below_status);
        mBtnShowBelowStatus.setOnClickListener(this);
        mBtnHideStatus = (Button) findViewById(R.id.btn_hide_status);
        mBtnHideStatus.setOnClickListener(this);
    }


    /*
    作者：dreamgyf
    链接：https://juejin.cn/post/7203563038301061181
    来源：稀土掘金
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * */
    void transparentStatusBar(Window window) {
        //start0
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //end0

        //start1 状态栏显示，且内容显示在状态栏下面
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        systemUiVisibility = systemUiVisibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        //end1

        window.setStatusBarColor(Color.TRANSPARENT);
        //设置状态栏文字颜色
        //setStatusBarTextColor(window, NightMode.isNightMode(window.context))

        //`setStatusBarColor()` 是一个用于 Android 平台的方法，用于设置状态栏的颜色。
        // 在较早的 Android 版本中，为了改变状态栏的颜色，需要在代码中使用
        // `WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS` 和 `WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS`
        // 这样的标志位来指示系统使状态栏可以被绘制，并去除状态栏的透明效果。
        //
        //然而，随着 Android 版本的更新和 API 的演变，Android 提供了更方便的方法来设置状态栏的颜色，如 `setStatusBarColor()`。
        // 这个方法是在 Android 5.0（API 级别 21）引入的。通过这个方法，开发者可以直接指定所需的状态栏颜色，而不再需要手动管理标志位来控制状态栏的透明度和背景。
        //
        //因此，如果你的应用目标的最低 API 级别是 21 或更高版本，你可以直接使用 `setStatusBarColor()` 方法来设置状态栏的颜色，而不需要手动管理那些标志位。
        //这个方法简化了代码，使得设置状态栏颜色更加方便和直观。
    }

    void restoreStatusBar(Window window) {
        // 清除设置的标志位和颜色
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK); // 设置默认状态栏颜色

        // 还原系统UI可见性设置
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        systemUiVisibility &= ~(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }


    boolean isFullScreen = false;
    boolean isBelowStatus = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_light_status:
                QMUIStatusBarHelper.setStatusBarLightMode(this);
                break;
            case R.id.btn_white_status:
                QMUIStatusBarHelper.setStatusBarDarkMode(this);
                break;
            case R.id.btn_status_color:
                //生成随机颜色的int值
                int color = Color.argb(255, (int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
                getWindow().setStatusBarColor(color);
                break;
            case R.id.btn_show_below_status:
                //fitsSystemWindows="true 注意xml中该属性的配合设置。该属性会默认状态栏的高度位置给空出来，保证布局不会重叠
                //不使用上面属性的话，要么占位StatusView，要么paddingTop
                if (!isBelowStatus) {
                    transparentStatusBar(this.getWindow());
                } else {
                    restoreStatusBar(this.getWindow());
                }
                isBelowStatus = !isBelowStatus;
                break;
            case R.id.btn_hide_status:
                if (!isFullScreen) {
                    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                isFullScreen = !isFullScreen;
                break;
        }
    }
}

//1.btn_hide_status。需要适配android 11
        /*
        一、隐藏标题栏
        //隐藏标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        二、隐藏状态栏
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        三、去掉所有Activity界面的标题栏
        修改AndroidManifest.xml
        在application 标签中添加android:theme="@android:style/Theme.NoTitleBar"
        四、去掉所有Activity界面的TitleBar 和StatusBar
        修改AndroidManifest.xml
        在application 标签中添加
        android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
        作者：huldaZhang
        链接：https://www.jianshu.com/p/404530a34db2
        来源：简书
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        */