package com.hanxuhui.netframework.util;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class ActivityCollector {

    private static final List<Activity> list = new LinkedList<>();

    public static void addActivity(Activity activity) {
        list.add(activity);
    }

    public static void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public static void removeAllActivity() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
