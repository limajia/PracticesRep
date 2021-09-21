package com.mlj.practicesrep.sqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

import java.util.Random;

import static com.mlj.practicesrep.sqllite.MySqlLite.TABLE_NAME;

/*SQLite是Android内置的一个小型、关系型、属于文本型的数据库。
Android提供了对 SQLite数据库的完全支持，应用程序中的--任何类---都可以通过名称来访问任何的数据库，但是--应用程序之外--的就不能访问。
Android中，通过SQLiteOpenHelper类来实现对SQLite数据库的操作。*/

// https://blog.csdn.net/legend12300/article/details/79037074 数据库操作
public class SqlLiteTestTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DB_NAME = "test_person";
    private static final int DB_VERSION = 2; //看到升级后，要有一个地方来记录升级后的verson.
    // 直接从2版本也是可以的

    private Button mAddBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;
    private Button mQueryBtn;
    private Button mUpgradeBtn;
    private TextView mDbResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite_test_test);
        initView();

    }

    private void initView() {
        mAddBtn = findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(this);
        mDeleteBtn = findViewById(R.id.delete_btn);
        mDeleteBtn.setOnClickListener(this);
        mUpdateBtn = findViewById(R.id.downgrade_btn);
        mUpdateBtn.setOnClickListener(this);
        mQueryBtn = findViewById(R.id.query_btn);
        mQueryBtn.setOnClickListener(this);
        mUpgradeBtn = findViewById(R.id.upgrade_btn);
        mUpgradeBtn.setOnClickListener(this);
        mDbResultText = findViewById(R.id.db_result_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                // 创建DatabaseHelper对象（记得传入最新版本的数据库）
                // 只执行这句话是不会创建或打开连接的
                //注意，一定要传入最新的数据库版本号
                MySqlLite mySqlLite = new MySqlLite(this, DB_NAME, null, DB_VERSION);
                SQLiteDatabase writableDatabase = mySqlLite.getWritableDatabase();

                ContentValues values = new ContentValues();
                //ContenValues Key只能是String类型，Value只能存储基本类型数据，不能存储对象
                values.put("name", "docker" + new Random().nextInt(100));
                writableDatabase.insert(TABLE_NAME, null, values);
                // 第二个参数：SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值
                // writableDatabase.execSQL("insert into user (id,name) values (1,'张三')")  也可以
                writableDatabase.close();
                doQuery();
                break;
            case R.id.delete_btn:
                String s = doQuery();
                if (s.equals("0")) {
                    return;
                }
                MySqlLite mySqlLite1 = new MySqlLite(this, DB_NAME, null, DB_VERSION);
                SQLiteDatabase writableDatabase1 = mySqlLite1.getWritableDatabase();
                writableDatabase1.delete(TABLE_NAME, "id=?", new String[]{s});
                writableDatabase1.close();
                doQuery();
                break;
            case R.id.query_btn:
                doQuery();
                break;
            case R.id.upgrade_btn:
//                MySqlLite mySqlLite2 = new MySqlLite(this, DB_NAME, null, 2);
//                SQLiteDatabase readableDatabase = mySqlLite2.getReadableDatabase();
                break;
            case R.id.downgrade_btn:
//                这种行为是错误的
//                MySqlLite mySqlLite3 = new MySqlLite(this, DB_NAME, null, 1);
//                SQLiteDatabase readableDatabase3 = mySqlLite3.getReadableDatabase();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private void doDBAction(String resultString) {
        mDbResultText.setText(resultString);
    }


    private String doQuery() {
        String lastId = "0";
        //第一种最为简单
        //将所有的SQL语句都组织到一个字符串中，使用占位符代替实际参数（selectionArgs）
       /* db.rawQuery(String sql, String[]selectionArgs);

        db.query(String table, String[]columns, String selection, String[]selectionArgs, String
        groupBy, String having, String orderBy);
        db.query(String table, String[]columns, String selection, String[]selectionArgs, String
        groupBy, String having, String orderBy, String limit);
        db.query(String distinct, String table, String[]columns, String selection, String[]
        selectionArgs, String groupBy, String having, String orderBy, String limit);*/

        /*//参数说明
        //table：要操作的表明
        //columns：查询的列所有名称集
        //selection：WHERE之后的条件语句，可以使用占位符
        //groupBy：指定分组的列名
        //having指定分组条件，配合groupBy使用
        //orderBy指定排序的列名
        //limit指定分页参数
        //distinct可以指定“true”或“false”表示要不要过滤重复值

        //所有方法将返回一个Cursor对象，代表数据集的游标

        //Cursor对象常用方法如下：
        c.move( int offset); //以当前位置为参考,移动到指定行
        c.moveToFirst();    //移动到第一行
        c.moveToLast();     //移动到最后一行
        c.moveToPosition( int position); //移动到指定行
        c.moveToPrevious(); //移动到前一行
        c.moveToNext();     //移动到下一行
        c.isFirst();        //是否指向第一条
        c.isLast();     //是否指向最后一条
        c.isBeforeFirst();  //是否指向第一条之前
        c.isAfterLast();    //是否指向最后一条之后
        c.isNull( int columnIndex);  //指定列是否为空(列基数为0)
        c.isClosed();       //游标是否已关闭
        c.getCount();       //总数据项数
        c.getPosition();    //返回当前游标所指向的行数
        c.getColumnIndex(String columnName);//返回某列名对应的列索引值
        c.getString( int columnIndex);   //返回当前行指定列的值
        */

        MySqlLite mySqlLite = new MySqlLite(this, DB_NAME, null, DB_VERSION);
        SQLiteDatabase readableDatabase = mySqlLite.getReadableDatabase();
        Cursor cursor = readableDatabase.query(TABLE_NAME, new String[]{"id",
                        "name"},
                null,
                null,
//                "id=?",
//                new String[]{"1"},
                null, null, null);

        // public AbstractCursor() {
        //        mPos = -1;
        //    }
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            stringBuilder.append(id);
            String name = cursor.getString(cursor.getColumnIndex("name"));
            stringBuilder.append(name);
            stringBuilder.append("\n");
            lastId = id;
        }
        doDBAction(stringBuilder.toString());
        readableDatabase.close();
        return lastId;
    }


    private SQLiteDatabase getReadDb() {
        // 创建DatabaseHelper对象
        // 只执行这句话是不会创建或打开连接的
        MySqlLite test_person = new MySqlLite(this, DB_NAME, null, DB_VERSION);
        // 调用getReadableDatabase()或getWritableDatabase()才算真正创建或打开数据库
        SQLiteDatabase readableDatabase = test_person.getReadableDatabase();
        return readableDatabase;
    }

    private SQLiteDatabase getWriteDb() {
        MySqlLite test_person = new MySqlLite(this, DB_NAME, null, DB_VERSION);
        SQLiteDatabase writableDatabase = test_person.getWritableDatabase();
        return writableDatabase;
    }
}