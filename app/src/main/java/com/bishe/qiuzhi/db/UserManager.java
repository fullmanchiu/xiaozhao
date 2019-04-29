package com.bishe.qiuzhi.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.bishe.qiuzhi.app.App;
import com.bishe.qiuzhi.module.login.model.LoginModel;

import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_AVATAR;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_MOBILE;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_TOKEN;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_USER_NAME;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_NAME;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_NICK_NAME;
import static com.bishe.qiuzhi.module.login.model.LoginModel.UserinfoBean.USER_TABLE_COLUMN_USER_ID;

public class UserManager {
    private static final String CONTENT_SCHEMA;
    private static final String AUTOHORITY;
    private static final String TAG = UserManager.class.getSimpleName();
    private static Context mContext;
    private static ContentResolver contentResolver = null;
    private static Uri uri;

    static {
        CONTENT_SCHEMA = "content://";
        AUTOHORITY = UserProvider.AUTHORITY;
        uri = Uri.parse(getProviderUri() + USER_TABLE_NAME);
    }

    private static String getProviderUri() {
        return CONTENT_SCHEMA + AUTOHORITY + "/";
    }

    public static void init(App app) {
        mContext = app;
        contentResolver = mContext.getContentResolver();
    }

    public static void saveUser(LoginModel.UserinfoBean user) {
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put(USER_TABLE_COLUMN_USER_ID, user.getUser_id());
        values.put(USER_TABLE_COLUMN_USER_NAME, user.getUsername());
        values.put(USER_TABLE_COLUMN_NICK_NAME, user.getNickname());
        values.put(USER_TABLE_COLUMN_MOBILE, user.getMobile());
        values.put(USER_TABLE_COLUMN_AVATAR, user.getAvatar());
        values.put(USER_TABLE_COLUMN_TOKEN, user.getToken());
        if (hasUser()) {
            contentResolver.update(uri, values, "id = ?", new String[]{"1"});
        } else {
            contentResolver.insert(uri, values);
        }
    }

    public static LoginModel.UserinfoBean getUser() {
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        LoginModel.UserinfoBean user = null;
        while (cursor.moveToNext()) {
            int userId = cursor.getInt(cursor.getColumnIndex(USER_TABLE_COLUMN_USER_ID));
            String userName = cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUMN_USER_NAME));
            String nickName = cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUMN_NICK_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUMN_MOBILE));
            String avatar = cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUMN_AVATAR));
            String token = cursor.getString(cursor.getColumnIndex(USER_TABLE_COLUMN_TOKEN));
            user = new LoginModel.UserinfoBean(userId, userName, nickName, mobile, avatar, token);
        }
        return user;
    }

    private static boolean hasUser() {
        boolean hasUser = false;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor.getCount() != 0) {
            hasUser = true;
        }
        return hasUser;
    }

    public static void deleteUser() {
        if (hasUser()) {
            contentResolver.delete(uri, null, null);
        }
    }
}
