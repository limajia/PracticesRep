package com.docker.handwrite.handglide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.docker.handwrite.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mImageView = findViewById(R.id.image_view);
        Glide.with(GlideActivity.this).load("https://goss2.cfp.cn/creative/vcg/800/new/VCG41N1210205351.jpg").into(mImageView);
    }
}