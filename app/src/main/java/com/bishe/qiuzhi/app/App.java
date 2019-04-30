package com.bishe.qiuzhi.app;

import android.app.Activity;
import android.app.Application;

import com.bishe.qiuzhi.db.UserManager;
import com.bishe.qiuzhi.module.login.model.LoginModel;

import java.util.HashSet;

public class App extends Application {
    private static App instance;
    private HashSet<Activity> allActivities;
    private LoginModel.UserinfoBean userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        UserManager.init(getInstance());
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity activity : allActivities) {
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setUserDate(LoginModel.UserinfoBean userinfo) {
        this.userInfo = userinfo;
        UserManager.saveUser(userinfo);
    }

    public LoginModel.UserinfoBean getUserData() {
        if (userInfo == null) {
            userInfo = UserManager.getUser();
        }
        return userInfo;
    }

    public boolean isLogin() {
        return UserManager.getUser() != null;
    }

    public void signOut() {
        userInfo = null;
        UserManager.deleteUser();
    }
}
