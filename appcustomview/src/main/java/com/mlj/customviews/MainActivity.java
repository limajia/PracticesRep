package com.mlj.customviews;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.customviews.R;
import com.mlj.customviews.CountdownView.CountDownViewActivity;
import com.mlj.customviews.customviewpractice.CustomViewPracticesActivity;
import com.mlj.customviews.layoutTransition.LayoutTransitionActivity;
import com.mlj.customviews.pullDownView.PullDownActivity;
import com.mlj.customviews.userEnter.UserEnterActivity;
import com.mlj.customviews.verticaldrawerlayout.VerticalDrawerLayoutActivity;

/**
 * @author docker
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      requestWindowFeature(Window.FEATURE_NO_TITLE); 或者配置noActionBar style
        setContentView(R.layout.activity_main);
        Button mGoCountDownView = findViewById(R.id.goCountDownView);
        mGoCountDownView.setOnClickListener(this);
        View pullDownView = findViewById(R.id.pullDownView);
        pullDownView.setOnClickListener(this);
        View userEnterView = findViewById(R.id.userEnterView);
        userEnterView.setOnClickListener(this);
        View layoutTransition = findViewById(R.id.layoutTransition);
        layoutTransition.setOnClickListener(this);
        findViewById(R.id.goVerticalDrawerLayout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        ComponentName componentName;
        switch (view.getId()) {
            case R.id.goVerticalDrawerLayout:
                componentName = new ComponentName(MainActivity.this, VerticalDrawerLayoutActivity.class);
                break;
            case R.id.goCountDownView:
                componentName = new ComponentName(MainActivity.this, CountDownViewActivity.class);
                break;
            case R.id.pullDownView:
                // 另外一种隐调用
                componentName = new ComponentName(MainActivity.this, PullDownActivity.class);
                break;
            case R.id.userEnterView:
                componentName = new ComponentName(MainActivity.this, UserEnterActivity.class);
                break;
            case R.id.layoutTransition:
                componentName = new ComponentName(MainActivity.this, LayoutTransitionActivity.class);
                break;
            case R.id.goCustomView:
                componentName = new ComponentName(MainActivity.this, CustomViewPracticesActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
        intent.setComponent(componentName);
        startActivity(intent);
    }
}