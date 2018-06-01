package com.example.duhongwang20180601.app;

import android.app.Application;
import com.example.duhongwang20180601.dao.DaoMaster;
import com.example.duhongwang20180601.dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.greenrobot.greendao.database.Database;

/**
 * Fresco  GreenDao  的初始化全局配置类
 */
public class MyApplication extends Application {
    //抽取为全局变量
    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        //1. Fresco
        Fresco.initialize(this);

        //2. GreenDao
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "rikao");
        Database db = helper.getWritableDb();
        session = new DaoMaster(db).newSession();
    }
}
