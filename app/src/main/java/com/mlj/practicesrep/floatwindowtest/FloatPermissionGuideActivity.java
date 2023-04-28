package com.mlj.practicesrep.floatwindowtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import com.mlj.practicesrep.R;

public class FloatPermissionGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_permission_guide);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(layout);
    }
}