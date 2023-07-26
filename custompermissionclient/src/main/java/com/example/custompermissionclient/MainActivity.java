package com.example.custompermissionclient;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

            //normal权限，声明了就有权限了，可以直接跳转
            /*Intent intent = new Intent();
            intent.setComponent(new android.content.ComponentName("com.mlj.practicesrep", "com.mlj.practicesrep.player.PlayerActivity"));
            startActivity(intent);*/

            //dangerous权限，需要用户同意权限，才可以跳转
            if (i == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{"com.mlj.practicesrep.permission.playerpermission"}, 1000);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
                //dangerous权限，需要用户同意权限，才可以跳转
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setComponent(new android.content.ComponentName("com.mlj.practicesrep", "com.mlj.practicesrep.player.PlayerActivity"));
                    startActivity(intent);
                }
        }
    }
}