package com.mlj.practicesrep.pagejump;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class PageJumpTestActivity extends AppCompatActivity {

    private Button mJumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_jump_test);
        mJumpBtn = findViewById(R.id.jumpBtn);
        mJumpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "myscheme://docker/mypath"));
            startActivity(intent);
        });
    }
}