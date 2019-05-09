package com.bishe.qiuzhi.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_NAME;

/**
 * 用户ContentProvider
 * 暴露用户数据
 */
public class UserProvider extends ContentProvider {
    public static final String AUTHORITY = "com.bishe.userprovider";
    private static final int USER_CODE = 1;
    private Context mContext;
    private SQLiteDbHelper mDBHelper = null;
    private SQLiteDatabase sdb = null;
    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTHORITY, USER_TABLE_NAME, USER_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDBHelper = new SQLiteDbHelper(mContext);
        sdb = mDBHelper.getWritableDatabase();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return sdb.query(getTableName(uri), projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sdb.insert(getTableName(uri), null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return sdb.delete(getTableName(uri), selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return sdb.update(getTableName(uri), values, selection, selectionArgs);
    }

    private String getTableName(Uri uri) {
        String table = null;
        switch (mMatcher.match(uri)) {
            case USER_CODE:
                table = USER_TABLE_NAME;
                break;
            default:
        }
        return table;
    }
}
