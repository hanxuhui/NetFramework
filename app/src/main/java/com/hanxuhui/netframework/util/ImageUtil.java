package com.hanxuhui.netframework.util;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanxuhui.netframework.application.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class ImageUtil {

    //保存某个ImageView控件上的image到本地
    public static void saveImage(final ImageView imageView, final String imageName) {
        Observable.create(new Observable.OnSubscribe<ImageView>() {
                              @Override
                              public void call(Subscriber<? super ImageView> sub) {
                                  sub.onNext(imageView);
                              }
                          }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageView>() {

                    @Override
                    public void onNext(ImageView imageView) {
                        File imageFile = new File(ZaUtil.getImagesFilePath(), imageName + ".jpg");
                        FileOutputStream outStream;
                        try {
                            outStream = new FileOutputStream(imageFile);
                            Bitmap image = imageView.getDrawingCache();
                            image.compress(Bitmap.CompressFormat.JPEG, 150, outStream);
                            outStream.flush();
                            outStream.close();
                            Toast.makeText(BaseApplication.getInstance(), "保存成功", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            onError(e);
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(BaseApplication.getInstance(), "保存失敗", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
