package com.hanxuhui.netframework.protocol.network;

import com.hanxuhui.netframework.protocol.bean.Response;
import com.hanxuhui.netframework.util.ToastUtil;
import com.hanxuhui.netframework.util.ZaLog;

import rx.Subscriber;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public abstract class ZaSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onNext(T t) {
        ZaLog.d("-----ZaSubscriber----onNext---------");
        //这里统一处理请求成功，但是需要统一处理的回调，如token失效
        if(t instanceof Response) {
            Response response = (Response)t;
            if(CommonResultCode.RESULT_SUCCESS == response.returnCode) {
                //请求成功
                onCallBack(t);
                ZaLog.d("---------onCallBack------------");
            }else if(CommonResultCode.RESULT_TOKEN_EXPIRED == response.returnCode) {
                //处理token失效
                ZaLog.d("---------token失效---------");
                ToastUtil.showLong("无效token");
            }else if(CommonResultCode.RESULT_VALIDATE_CODE_EXPIRED == response.returnCode) {
                //处理token失效
                ZaLog.d("---------验证码不正确---------");
                ToastUtil.showLong("验证码不正确");
            }else {
                ToastUtil.showLong(response.returnMsg);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        ZaLog.d("--------ZaSubscriber---------------onError--------------");
        //这里，在onNext方法中，处理数据时出错会调到该方法中
        ToastUtil.showLong("onNext method deal data error");
    }

    protected abstract void onCallBack(T t);

}
