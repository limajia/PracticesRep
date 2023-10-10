package com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.docker.handwrite.R;
import com.docker.lib_annotation.BindView;

@BindView()
public class TestButterKnifeMainActivity extends AppCompatActivity {

    @BindView(R.id.test_butter_knife_view)
    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_butter_knife_main);
        // 新文件为注解对象赋值
        AptBinding.bind(this);
        mTextView.setText("测试一下ButterKnife11111");
    }
}

