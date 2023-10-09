package com.doker11.contentproviderclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.os.Bundle;

//  安卓手机通讯录存放位置:data/data/com.android.provides.contacts。
/*
    ContentProvider并不是自己暴露自己的数据，更多的时候通过 ContentResolver来读取其他应用的信息，
    最常用的莫过于读取系统APP，信息，联系人， 多媒体信息等！如果你想来调用这些ContentProvider就需要自行查阅相关的API资料了！
    另外，不同的版本，可能对应着不同的URL！这里给出如何获取URL与对应的数据库表的字段， 这里以最常用的联系人为例，其他自行google~
    ①来到系统源码文件下:all-src.rar -> TeleponeProvider -> AndroidManifest.xml查找对应API
    ②打开模拟器的file exploer/data/data/com.android.providers.contacts/databases/contact2.db
    导出后使用SQLite图形工具查看，三个核心的表:raw_contact表，data表，mimetypes表！
*/
public class ClientSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        // 手机联系人
        ContentResolver contentResolver = getContentResolver();
    }
}
       