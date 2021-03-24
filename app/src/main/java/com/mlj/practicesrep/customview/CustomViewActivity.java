package com.mlj.practicesrep.customview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
//        view.getResources()
//        getApplicationContext().getResources()
//        context().getResources()
//        Resources.getSystem().getDisplayMetrics() //只会得到系统参数的上下文
    }
}