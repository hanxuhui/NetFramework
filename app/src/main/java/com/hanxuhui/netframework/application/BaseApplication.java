package com.hanxuhui.netframework.application;

import android.app.Application;

import com.hanxuhui.netframework.util.ToastUtil;
import com.hanxuhui.netframework.util.ZaLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;
    //內存泄漏检测
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                ZaLog.e("error", e.getMessage());
            }
        });
        mRefWatcher = LeakCanary.install(this);
        ToastUtil.register(this);
    }

    public static BaseApplication getInstance(){
        return instance;
    }

}
