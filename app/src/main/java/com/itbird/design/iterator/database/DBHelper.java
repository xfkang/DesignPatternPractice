package com.itbird.design.iterator.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 新建数据库表table_test，为了测试方便直接插入数据
 * Created by itbird on 2022/7/11
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "db_test";
    public static final String TABLE_NAME = "table_test";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    public DBHelper(Context context) {
        //新建数据库db_test，version为1
        super(context, DB_NAME, null, 1);
    }

    public void init() {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTERGER PRIMARY KEY AUTOINCREMENT,name TEXT)");
        db.execSQL("INSERT INTO  " + TABLE_NAME + " (name) values ('xiaokang')");
        db.execSQL("INSERT INTO  " + TABLE_NAME + " (name) values ('xiaomingming')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
