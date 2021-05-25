package com.mlj.practicesrep.clickviewgroup;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class TestClickViewGroupActivity extends AppCompatActivity {

    private RelativeLayout mBottomLayout;
    private RelativeLayout mTopLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_click_view_group);
        mBottomLayout = findViewById(R.id.bottom_layout);
        mTopLayout = findViewById(R.id.top_layout);
        mBottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestClickViewGroupActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}