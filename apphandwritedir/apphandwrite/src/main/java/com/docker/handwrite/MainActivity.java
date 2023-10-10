package com.docker.handwrite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.docker.handwrite.handbutterknife.lib_plan_processor.aptuseverson.TestButterKnifeMainActivity;
import com.docker.handwrite.handeventbus.EventBusActivity;
import com.docker.handwrite.handglide.GlideActivity;

/**
 * @author docker
 */
public class MainActivity extends AppCompatActivity {

    private Button mEventBusBtn;
    private Button mGlideBtn;
    private Button mButterknifeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEventBusBtn = findViewById(R.id.event_bus_btn);
        //1.handEventBus
        mEventBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        });
        //2.handGlide
        mGlideBtn = findViewById(R.id.glide_btn);
        mGlideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
            }
        });
        //3.handbutterknife
        mButterknifeBtn = findViewById(R.id.butterknife_btn);
        mButterknifeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestButterKnifeMainActivity.class);
                startActivity(intent);
            }
        });
    }
}