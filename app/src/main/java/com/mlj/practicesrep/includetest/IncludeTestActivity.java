package com.mlj.practicesrep.includetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class IncludeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include_main);
        View innerId = findViewById(R.id.index_linear_foot);
        if (innerId != null) {
            Toast.makeText(IncludeTestActivity.this, "innerId不为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(IncludeTestActivity.this, "外层设置id后，内层的id找不到了，" +
                    "将外层的ID替换到了内层的View上", Toast.LENGTH_SHORT).show();
        }
    }
}