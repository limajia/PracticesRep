package com.doker11.contentproviderclient;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ClientCustomActivity extends AppCompatActivity {

    private Button queryContent;
    private Button insertContent;
    private TextView contentTextview;
    //读取contentprovider 数据
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolver = getContentResolver();
        setContentView(R.layout.activity_client_custom);
        initView();
    }

    private void initView() {
        contentTextview = findViewById(R.id.content_textview);
        queryContent = findViewById(R.id.query_content);
        queryContent.setOnClickListener(v -> {

        });
        insertContent = findViewById(R.id.insert_content);
        insertContent.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            int randomInt = new Random().nextInt(100);
            values.put("name" + randomInt, "测试" + randomInt);
            Uri uri = Uri.parse("content://com.docker11.NameContentProvider/testInsertPath");
            Uri insert = resolver.insert(uri, values);
            if (insert != null) {
                Toast.makeText(getApplicationContext(), "数据插入成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}