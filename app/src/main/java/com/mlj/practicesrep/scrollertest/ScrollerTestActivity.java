package com.mlj.practicesrep.scrollertest;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

/**
 * @author docker
 * 滚动测试
 */
public class ScrollerTestActivity extends AppCompatActivity {

    private Button mCase1TestBtn;
    private Case1ViewGroup mCase1ViewGroup;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_test);

        mCase1TestBtn = findViewById(R.id.Case1TestBtn);
        mCase1ViewGroup = findViewById(R.id.case1ViewGroup);
        mCase1TestBtn.setOnClickListener(v -> {

            mCase1ViewGroup.moveToIndex((++index) % 6);
        });
    }
}