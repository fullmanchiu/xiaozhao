package com.bishe.qiuzhi.app;

import android.app.Activity;
import android.app.Application;

import com.bishe.qiuzhi.db.UserManager;
import com.bishe.qiuzhi.module.login.model.LoginModel;

import java.util.HashSet;

/**
 * @author fullm
 * Description: 整个Application的生命周期
 */
public class App extends Application {
    private static App instance; //app对象
    private HashSet<Activity> allActivities;//记录所有当前为destory的activity
    private LoginModel.UserinfoBean userInfo;//用户信息

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;//给app对象赋值
        UserManager.init(getInstance());//初始换UserManager
    }

    /**
     * 单例获取App对象
     * @return instance app
     */
    public static synchronized App getInstance() {
        return instance;
    }

    /**
     * 将 activity 加入 HashSet
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    /**
     * 将 activity 从 HashSet 中移除
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    /**
     * 退出程序
     * 将 HashSet 中的 activity finish
     */
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

    /**
     * 保存用户信息
     * @param userinfo
     */
    public void setUserDate(LoginModel.UserinfoBean userinfo) {
        this.userInfo = userinfo;
        UserManager.saveUser(userinfo);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public LoginModel.UserinfoBean getUserData() {
        if (userInfo == null) {
            userInfo = UserManager.getUser();
        }
        return userInfo;
    }

    /**
     * 判断是否已经登陆
     * @return
     */
    public boolean isLogin() {
        return UserManager.getUser() != null;
    }

    /**
     * 退出登录
     */
    public void signOut() {
        userInfo = null;
        UserManager.deleteUser();
    }
}
