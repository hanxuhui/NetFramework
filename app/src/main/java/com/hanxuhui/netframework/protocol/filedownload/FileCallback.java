package com.hanxuhui.netframework.protocol.filedownload;

import com.hanxuhui.netframework.util.RxBus;
import com.hanxuhui.netframework.util.ZaLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hanxuhui on 2016/7/14.
 */
public abstract class FileCallback implements Callback<ResponseBody> {

    // 订阅下载进度
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();
    // 目标文件存储的文件夹路径
    private String destFileDir;
    // 目标文件存储的文件名
    private String destFileName;

    public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress();
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            saveFile(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存
     *
     * @param response
     * @return
     * @throws IOException
     */
    public File saveFile(Response<ResponseBody> response) throws IOException {
        ZaLog.d("----------------saveFile----------------------");
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            byte[] buf = new byte[8192];
            fos = new FileOutputStream(file);
            int len;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            onSuccess();
            return file;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 订阅文件下载进度
     */
    private void subscribeLoadProgress() {
        rxSubscriptions.add(RxBus.getInstance()
                .toObservable(FileEvent.class)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FileEvent>() {
                    @Override
                    public void call(FileEvent fileLoadEvent) {
                        onProgress(fileLoadEvent.getBytesRead(), fileLoadEvent.getContentLength(), fileLoadEvent.isDone());
                    }
                }));
    }

    public void onProgress(long progress, long total, boolean isDone){
        progress(progress, total, isDone);
    }

    public void onSuccess() {
        unsubscribe();
        success();
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        unsubscribe();
        call.cancel();
        failure();
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    private void unsubscribe() {
        if (!rxSubscriptions.isUnsubscribed()) {
            rxSubscriptions.unsubscribe();
        }
    }

    public abstract void progress(long progress, long total, boolean isDone);

    public abstract void success();

    public abstract void failure();

}
