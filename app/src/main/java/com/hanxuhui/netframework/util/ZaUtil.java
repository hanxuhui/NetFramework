package com.hanxuhui.netframework.util;

import android.content.Context;
import android.os.Environment;

import com.hanxuhui.netframework.application.BaseApplication;

import java.io.File;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class ZaUtil {

    public static String getFilePath() {
        return getSavePath("files");
    }

    public static String getImagesFilePath() {
        return getSavePath("images");
    }

    public static String getCacheFilePath() {
        return getSavePath("caches");
    }

    /**
     * 获得文件存储的绝对路径
     * @param dirName       可传 images,voices,errorLogs,files
     * @return
     */
    public static String getSavePath(String dirName) {
        String path;
        Context context = BaseApplication.getInstance();
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //有SD卡
            path = Environment.getExternalStorageDirectory()
                    + File.separator
                    + "Android"
                    + File.separator
                    + "Data"
                    + File.separator
                    + context.getPackageName()
                    + File.separator
                    + dirName
                    + File.separator;
        } else {
            //无SD卡
            path = context.getFilesDir().getAbsolutePath() + File.separator + dirName + File.separator;
        }
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }

}
