package com.mlj.practicesrep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mlj.practicesrep.broadcast.BroadCastActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.
        View broadCastTestBtn = findViewById(R.id.broadCastTestBtn);
        broadCastTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BroadCastActivity.class);
            startActivity(intent);
        });
    }
}