package com.mlj.practicesrep.sqllite;

import static com.mlj.practicesrep.sqllite.MySqlLite.TABLE_NAME;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

import java.util.Random;

//从源码来看getReadableDatabase()的误区, getWrite和getRead得到的是同一个对象
// https://blog.csdn.net/zheng_weichao/article/details/79202040
// 这里测试的是多线程操作数据库，是不会崩溃的，但是数据库中的结果是有数据安全问题的（有重复的）

public class SqlLiteTestTestActivity2 extends AppCompatActivity implements View.OnClickListener {

    private static final String DB_NAME = "test_person";
    private static final int DB_VERSION = 2;
    private TextView dbResultText;
    private Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_test_test2);
        initView();
    }

    private SQLiteDatabase getNewWriteDbBase() {
        MySqlLite test_person = new MySqlLite(this, DB_NAME, null, DB_VERSION);
        SQLiteDatabase writableDatabase = test_person.getWritableDatabase();
        return writableDatabase;
    }

    private void initView() {
        dbResultText = findViewById(R.id.db_result_text);
        startBtn = findViewById(R.id.start_text);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_text:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SQLiteDatabase newWriteDbBase = getNewWriteDbBase();
                        for (int i = 0; i < 1000; i++) {
                            ContentValues values = new ContentValues();
                            values.put("name", "dockerinner_" + i);
                            newWriteDbBase.insert(TABLE_NAME, null, values);
                        }
                    }
                }.start();
                SQLiteDatabase newWriteDbBase = getNewWriteDbBase();
                for (int i = 0; i < 1000; i++) {
                    ContentValues values = new ContentValues();
                    values.put("name", "dockerouter_" + i);
                    newWriteDbBase.insert(TABLE_NAME, null, values);
                }
                break;
        }
    }
}