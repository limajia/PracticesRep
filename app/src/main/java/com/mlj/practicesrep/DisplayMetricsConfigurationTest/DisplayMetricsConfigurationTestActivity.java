package com.mlj.practicesrep.DisplayMetricsConfigurationTest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.App;
import com.mlj.practicesrep.R;

public class DisplayMetricsConfigurationTestActivity extends AppCompatActivity {

    private TextView tvTestTextview;
    private TextView tvShowparamTextview;
    private TextView tvTestBtnAddApp;
    private TextView tvTestBtnAddActivity;
    private TextView tvTestBtnJumpNextApp;
    private TextView tvTestBtnJumpNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_metrics_configuration_test);
        initView();
    }

    private void initView() {
        tvTestTextview = (TextView) findViewById(R.id.tv_test_textview);
        tvShowparamTextview = (TextView) findViewById(R.id.tv_showparam_textview);
        tvShowparamTextview.setText("Activity:__" + getParm(this) + "\n\nApp:__" + getParm(App.getApp()));
        tvTestBtnAddApp = (TextView) findViewById(R.id.tv_test_btn_app_add);
        tvTestBtnAddApp.setOnClickListener(v -> {
            App.getApp().getResources().getDisplayMetrics().density += 0.1f;
            tvShowparamTextview.setText("Activity:__" + getParm(this) + "\n\nApp:__" + getParm(App.getApp()));
        });
        tvTestBtnAddActivity = (TextView) findViewById(R.id.tv_test_btn_activity_add);
        tvTestBtnAddActivity.setOnClickListener(v -> {
            getResources().getDisplayMetrics().density += 0.1f;
            tvShowparamTextview.setText("Activity:__" + getParm(this) + "\n\nApp:__" + getParm(App.getApp()));
//            getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());
//            Context configurationContext = createConfigurationContext(getResources().getConfiguration());
        });
        tvTestBtnJumpNextApp = (TextView) findViewById(R.id.tv_test_btn_jump_next_app);
        tvTestBtnJumpNextApp.setOnClickListener(v -> {
            //跳转当前页面 以下异常有版本差异。
            //Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getApp().startActivity(intent);
        });
        tvTestBtnJumpNextActivity = (TextView) findViewById(R.id.tv_test_btn_jump_next_activity);
        tvTestBtnJumpNextActivity.setOnClickListener(v -> {
            //跳转当前页面
            startActivity(getIntent());

            //getResources().getDrawable()
        });
    }

    private String getParm(Context context) {
        return "resoucecode:" + getResouce(context) + "--" + "dismeticcode:" + getDisplayMetrics(context) + context.getResources().getDisplayMetrics().toString();
    }

    private String getResouce(Context context) {
        return context.getResources().hashCode() + "";
    }

    private String getDisplayMetrics(Context context) {
        return System.identityHashCode(context.getResources().getDisplayMetrics()) + "";
    }

    //结论就是：Activity和App的DisplayMetrics是不一样的，Resource也是不一样的。
    //Activity的DisplayMetrics是同一个，而Resource是不一样的。
}