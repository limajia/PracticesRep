package com.docker.handwrite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson.TestButterKnifeMainActivity;
import com.docker.handwrite.handeventbus.EventBusActivity;
import com.docker.handwrite.handglide.GlideActivity;
import com.docker.handwrite.handokhttp.OkHttpActivity;

/**
 * @author docker
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //4.handOKHttp
        Button mOkHttpBtn = findViewById(R.id.okhttp_btn);
        mOkHttpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OkHttpActivity.class);
            startActivity(intent);
        });
        //3.handbutterknife
        Button mButterknifeBtn = findViewById(R.id.butterknife_btn);
        mButterknifeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestButterKnifeMainActivity.class);
            startActivity(intent);
        });
        //2.handGlide
        Button mGlideBtn = findViewById(R.id.glide_btn);
        mGlideBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GlideActivity.class);
            startActivity(intent);
        });
        //1.handEventBus
        Button mEventBusBtn = findViewById(R.id.event_bus_btn);
        mEventBusBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
            startActivity(intent);
        });
    }
}