package com.bishe.qiuzhi.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_AVATAR;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_MOBILE;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_NICK_NAME;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_TOKEN;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_USER_ID;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_USER_NAME;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_NAME;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String TAG = "SQLiteDbHelper";
    public static final String DB_NAME = "xiaozhao.db";
    public static final int DB_VERSION = 1;
    private static final String SQL_CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME
            + " ( id INT PRIMARY KEY NOT NULL , "
            + USER_TABLE_COLUMN_USER_ID + " NVARCHAR(128) NOT NULL, "
            + USER_TABLE_COLUMN_USER_NAME + " NVARCHAR(128), "
            + USER_TABLE_COLUMN_NICK_NAME + " NVARCHAR(128), "
            + USER_TABLE_COLUMN_MOBILE + " NVARCHAR(32), "
            + USER_TABLE_COLUMN_AVATAR + " NVARCHAR(1024), "
            + USER_TABLE_COLUMN_TOKEN + " NVARCHAR(128) NOT NULL);";

    public SQLiteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public SQLiteDbHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public SQLiteDbHelper(Context context, String name) {
        this(context, name, DB_VERSION);
    }

    public SQLiteDbHelper(Context context) {
        this(context, DB_NAME);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "first run onCreate create tables");
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade oldVersion:" + oldVersion + " newVersion:" + newVersion);
    }
}
