package com.cahill.fisher;

import android.app.Application;
import android.content.Context;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:39
 * @desc
 */
public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getMyApplicationContext() {
        return context;
    }
}
