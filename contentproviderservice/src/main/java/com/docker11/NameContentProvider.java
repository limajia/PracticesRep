package com.docker11;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.docker11.db.DBOpenHelper;

public class NameContentProvider extends ContentProvider {
    // 就是一个存储 uri和path 与Result code对应的一个映射类，当没有匹配成功的时候，返回-1，即：UriMatcher.NO_MATCH
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DBOpenHelper dbOpenHelper;

    private static final int sInsertResultCode = 1;
    private static final int sQueryResultCode = 2;
    private static final String sUriPath = "test";

    //为了方便直接使用UriMatcher,这里addURI,下面再调用Matcher进行匹配

    static {
        // 这里是权利中介名，类似host 、path  、 resultCode
        // 这里为了方便，指定了和文件全路径名一样的的权利中介名
        matcher.addURI("com.docker11.NameContentProvider", "testInsertPath", sInsertResultCode);
        matcher.addURI("com.docker11.NameContentProvider", "testQueryPath", sQueryResultCode);
    }


    @Override
    public boolean onCreate() {
        System.out.println("docker11345：NameContentProvider onCreate方法执行了");
        // 只会执行一次
        dbOpenHelper = new DBOpenHelper(this.getContext(), "test.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (matcher.match(uri)) {
            case sInsertResultCode:
                SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
                long rowId = writableDatabase.insert("testContentDb", null, values); //这个table名称是如何确认的？？？？？ //就是业务上的table和注册的UriMather中的path没有关系
                if (rowId > 0) {
                    //在前面已有的Uri后面追加ID
                    Uri nameUri = ContentUris.withAppendedId(uri, rowId);
                    //通知数据已经发生改变
                    getContext().getContentResolver().notifyChange(nameUri, null);
                    return nameUri;
                }
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
