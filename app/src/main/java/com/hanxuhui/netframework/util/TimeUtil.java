package com.hanxuhui.netframework.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class TimeUtil {

    private static SimpleDateFormat format;

    // 获取本地时间
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        Date d = new Date();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    // 时间戳转换成字符串
    @SuppressLint("SimpleDateFormat")
    public static String getDateToString(long time) {
        Date d = new Date(time);
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(d);
    }

    // 字符串转换成时间戳
    @SuppressLint("SimpleDateFormat")
    public static long getStringToDate(String time) {
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
