package com.hanxuhui.netframework.protocol.network;

import com.hanxuhui.netframework.util.NetUtil;
import com.hanxuhui.netframework.util.ZaLog;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class NetWorkInterceptor implements Interceptor {

    //设缓存有效期为30天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 30;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!NetUtil.isNetworkConnected()) {
            request = request
                    .newBuilder()
                    //强制使用缓存
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            ZaLog.e("----------------no network--------------------");
        }

        Response originalResponse = chain.proceed(request);

        if (NetUtil.isNetworkConnected()) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse
                    .newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC)
                    .removeHeader("Pragma")
                    .build();
        }
    }

}
