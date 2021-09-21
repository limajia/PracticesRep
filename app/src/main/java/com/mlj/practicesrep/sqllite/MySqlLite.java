package com.mlj.practicesrep.sqllite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mlj.practicesrep.App;

// 注意拼写 只有一个L
// SQLiteOpenHelper: This class makes it easy for {@link android.content.ContentProvider}

public class MySqlLite extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "person";
    public static final String NAME_VALUE = "name";

    private static final String NAME_VALUE_FIXED = "personname";
    public static int version = 1;

    public MySqlLite(@Nullable Context context,
                     @Nullable String name,
                     @Nullable SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }

    public MySqlLite(@Nullable Context context,
                     @Nullable String name,//
                     @Nullable SQLiteDatabase.CursorFactory factory,
                     int version,//
                     @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    //第一次创建数据库的时候回调该方法
    //当使用getReadableDatabase()方法获取数据库实例的时候, 如果数据库不存在, 就会调用这个方法;
    //作用：创建数据库表：将创建数据库表的 execSQL()方法 和 初始化表数据的一些 insert()方法写在里面;
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLite数据创建支持的数据类型： 整型数据，字符串类型，日期类型，二进制的数据类型
        //创建了一个名为person的表
        String sql = "create table person(id integer primary key autoincrement," +
                NAME_VALUE
                + " varchar(64),address varchar(64))";
        //execSQL用于执行SQL语句
        //完成数据库的创建
        db.execSQL(sql);
        // 数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
    }

    //作用：更新数据库表结构
    //调用时机：数据库版本发生变化的时候回调（取决于数据库版本）,getWritableDatabase() 或者 getReadableDatabase()执行时候。
    // 创建SQLiteOpenHelper子类对象的时候,必须传入一个version参数
    //该参数就是当前数据库版本, 只要这个版本高于之前的版本, 就会触发这个onUpgrade()方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        version = newVersion;
        // 最直接的方法 直接删除老的数据库  读取数据库的之后指定一个更高的newVersion
        // db.execSQL("drop table if exists Book");
        //    onCreate(db);

        // 这里我们修改 name属性为personname SQLite 目前还不支持直接修改字段
//        if (oldVersion == 1 && newVersion == 2) {
//            //使用SQL的ALTER语句
//            String sql = "alter table person add sex varchar(8)";
//            db.execSQL(sql);
//        }


        if (newVersion == 1 && oldVersion == 2) {
          /*  Toast.makeText(App.getApp(), "降级", Toast.LENGTH_SHORT).show();
//            db.execSQL("alter table person drop column newColumn integer");
            db.execSQL("drop table if exists person");*/
//            onCreate(db);
//            这里是错误的，不会有这种newVersion<oldVersion的情况，会直接报错  使用降级回调
        } else {
            Toast.makeText(App.getApp(), "升级", Toast.LENGTH_SHORT).show();
            db.execSQL("alter table person add column newColumn integer");
        }


        //Can't downgrade database from version 2 to 1
//        如果升级后，还去拿1 则会报错
    }

    //    https://blog.csdn.net/u011414643/article/details/70800902 数据库的降级操作
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onDowngrade(db, oldVersion, newVersion);
        //需要在事务中进行
//        if (newVersion < oldVersion) {
//            db.beginTransaction();
//            try {
//                //Update DataBase SQL...
//                db.execSQL("DROP TABLE IF EXISTS bll");
//                db.execSQL("DROP TABLE IF EXISTS bs");
//                db.execSQL("DROP TABLE IF EXISTS bat");
//                db.execSQL("DROP TABLE IF EXISTS bite");
//                db.execSQL("DROP TABLE IF EXISTS bdor");
//                db.execSQL("DROP TABLE IF EXISTS bf");
//                db.execSQL("DROP TABLE IF EXISTS bnce");
//                db.execSQL("DROP TABLE IF EXISTS brs");
//                onCreate(db); // 在数据保存好的前提下，进行降级表结构创建，并进行数据的迁移即可.
//                db.setTransactionSuccessful();
//            } finally {
//                db.endTransaction();
//            }
//        }
    }

    // 数据库打开 会回调这里
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        version = db.getVersion();
    }

    /*execSQL()；
    可进行增删改操作,不能进行查询操作。
    insert()
    插入数据。
    delete()
    删除数据。*/

    /*query()、rawQuery()
    查询数据库*/

    /*1.对于“增、删、改（更新）”这类对表内容变换的操作，需先调用getWritableDatabase()获得一个可写数据库对象，
    在执行的时候调用通用的execSQL(String sql)或或对应的操作API方法：insert()、delete()、update()
    2.对“查”，需要调用getReadableDatabase()获得一个可读的数据库对象，然后使用query()或rawQuery()方法
            查询数据库不能使用execSQL方法*/

}
