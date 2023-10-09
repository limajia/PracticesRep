package com.doker11.contentproviderclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClientMainActivity extends AppCompatActivity {

    private Button gotoSystem;
    private Button gotoCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clent_main);
        initView();
    }

    private void initView() {
        gotoSystem = (Button) findViewById(R.id.goto_system);
        gotoSystem.setOnClickListener(v -> {
            Intent intent = new Intent(ClientMainActivity.this, ClientSystemActivity.class);
            ClientMainActivity.this.startActivity(intent);
        });
        gotoCustom = (Button) findViewById(R.id.goto_custom);
        gotoCustom.setOnClickListener(v -> {
            Intent intent = new Intent(ClientMainActivity.this, ClientCustomActivity.class);
            ClientMainActivity.this.startActivity(intent);
        });
    }
}