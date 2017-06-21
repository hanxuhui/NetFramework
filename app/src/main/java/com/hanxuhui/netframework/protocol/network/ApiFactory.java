package com.hanxuhui.netframework.protocol.network;

import com.hanxuhui.netframework.BuildConfig;
import com.hanxuhui.netframework.application.BaseApplication;
import com.hanxuhui.netframework.protocol.ApiClient;
import com.hanxuhui.netframework.protocol.ApiGroupClient;
import com.hanxuhui.netframework.util.ZaUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hanxuhui on 2016/6/26.
 * 使用retrofit创建ApiClient实例
 */
public class ApiFactory {

    private static ApiClient apiClient;
    private static ApiGroupClient apiGroupClient;
    private static OkHttpClient okHttpClient;

    public static ApiClient getApiClientInstance() {
        if(apiClient == null) {
            synchronized (ApiFactory.class) {
                if(apiClient == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiClient.HOST_HTTP)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                    apiClient = retrofit.create(ApiClient.class);
                }
            }
        }
        return apiClient;
    }

    public static ApiGroupClient getApiGroupClientInstance() {
        if(apiGroupClient == null) {
            synchronized (ApiFactory.class) {
                if(apiGroupClient == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(apiGroupClient.HOST_HTTP)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                    apiGroupClient = retrofit.create(ApiGroupClient.class);
                }
            }
        }
        return apiGroupClient;
    }

    private static OkHttpClient getOkHttpClient() {
        if(okHttpClient == null) {
            synchronized (ApiFactory.class) {
                if (okHttpClient == null) {
                    //添加body日志打印
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
                    //web缓存, 30M
                    Cache cache = new Cache(new File(ZaUtil.getSavePath("okHttpCache")), 1024 * 1024 * 30);
                    okHttpClient = new OkHttpClient.Builder()
                            //设置一个自动管理cookies的管理器
                            .cookieJar(new CookiesManager())
                            .addInterceptor(new HeaderInterceptor())
                            .addInterceptor(httpLoggingInterceptor)
//                            .addNetworkInterceptor(new NetWorkInterceptor())
                            //设置请求读写的超时时间
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .cache(cache)
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 自动管理Cookies
     */
    private static class CookiesManager implements CookieJar {

        private final PersistentCookieStore cookieStore = new PersistentCookieStore(BaseApplication.getInstance());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }

}
