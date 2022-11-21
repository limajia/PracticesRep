package com.mlj.practicesrep.constraint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.mlj.practicesrep.R;

public class ConstraintTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long start = System.currentTimeMillis();
       /* View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_constraint_test1, null, false);
        long test1End = System.currentTimeMillis();
        System.out.println("docker11: const=" + (test1End - start));*/
        View inflate2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_constraint_test2, null, false);
        long test2End = System.currentTimeMillis();
        System.out.println("docker11: relative=" + (test2End - start));
        setContentView(inflate2);

        // 结论是层级简单的，约束布局还不如Relative快。
    }
}