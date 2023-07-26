package com.example.custompermissionclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private TextView jumpToOtherApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        jumpToOtherApp = (TextView) findViewById(R.id.jumpToOtherApp);
        jumpToOtherApp.setOnClickListener(v -> {
            int i = ActivityCompat.checkSelfPermission(this, "com.mlj.practicesrep.permission.playerpermission");
            System.out.println("docker345: result=" + i);

            //有权限了可以跳转
            Intent intent = new Intent();
            intent.setComponent(new android.content.ComponentName("com.mlj.practicesrep", "com.mlj.practicesrep.player.PlayerActivity"));
            startActivity(intent);
        });
    }
}