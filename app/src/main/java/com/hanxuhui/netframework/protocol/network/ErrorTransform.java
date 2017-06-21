package com.hanxuhui.netframework.protocol.network;

import android.util.Log;
import android.widget.Toast;

import com.hanxuhui.netframework.R;
import com.hanxuhui.netframework.application.BaseApplication;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hanxuhui on 2016/6/26.
 * 更优雅的转换Observable去统一处理错误
 */
public class ErrorTransform<T> implements Observable.Transformer<T, T> {

    private static final String TAG = "ErrorTransform";

    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.onErrorResumeNext(new Func1<Throwable, Observable<? extends T>>() {
            @Override
            public Observable<? extends T> call(Throwable throwable) {
                //判断异常是什么类型
                Log.i(TAG, throwable.getClass().getName() + " " + throwable.getLocalizedMessage() + " " + throwable.getMessage());
                String errorMessage;
                //通过状态码判断错误
                if (throwable instanceof HttpException) {
                    errorMessage = BaseApplication.getInstance().getResources().getString(R.string.server_no_response);
                } else if (throwable instanceof ConnectException) {
                    errorMessage = BaseApplication.getInstance().getResources().getString(R.string.no_network_connect);
                } else if (throwable instanceof SocketTimeoutException) {
                    errorMessage = BaseApplication.getInstance().getResources().getString(R.string.network_connect_timeout);
                } else if (throwable instanceof RuntimeException) {
                    errorMessage = BaseApplication.getInstance().getResources().getString(R.string.server_inner_error);
                } else {
                    errorMessage = BaseApplication.getInstance().getResources().getString(R.string.host_connect_refused);
                }
                Toast.makeText(BaseApplication.getInstance(), errorMessage, Toast.LENGTH_SHORT).show();
                return Observable.empty();
            }
        });
    }
}
