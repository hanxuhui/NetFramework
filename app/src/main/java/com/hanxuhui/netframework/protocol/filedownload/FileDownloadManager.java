package com.hanxuhui.netframework.protocol.filedownload;

import com.hanxuhui.netframework.util.ZaLog;
import com.hanxuhui.netframework.util.ZaUtil;

/**
 * Created by hanxuhui on 2016/7/14.
 */
public class FileDownloadManager {

    public static void startDownload() {
        String baseUrl = "http://static.zhongan.com/website/other/download/app/papa/app-zhongan-release.apk";
        String fileName = "papa.apk";
        String fileStoreDir = ZaUtil.getFilePath();
        String fileStoreName = fileName;
        ApiFileFactory.getInstance()
                .createApiRxDownloadClient()
                .downloadFile(baseUrl)
                .enqueue(new FileCallback(fileStoreDir, fileStoreName) {
                    @Override
                    public void progress(long progress, long total, boolean isDone) {
                        ZaLog.e("-------" + progress + "/" + total +"---------");
                    }

                    @Override
                    public void success() {
                        ZaLog.d("-------------success--------------");
                    }

                    @Override
                    public void failure() {
                        ZaLog.d("-------------failure--------------");
                    }
                });
    }


}
