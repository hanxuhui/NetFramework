package com.hanxuhui.netframework.protocol.filedownload;

import com.hanxuhui.netframework.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by hanxuhui on 2016/7/14.
 */
public class ApiFileFactory {

    private static final String baseUrl = "http://10.139.105.94:7080/";

    private static ApiFileFactory apiRxFactory;

    private ApiFileFactory() {}

    public static ApiFileFactory getInstance() {
        if(apiRxFactory == null) {
            synchronized (ApiFileFactory.class) {
                if(apiRxFactory == null) {
                    apiRxFactory = new ApiFileFactory();
                }
            }
        }
        return apiRxFactory;
    }

    public ApiFileClient createApiRxDownloadClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .build();
        return retrofit.create(ApiFileClient.class);
    }

    /**
     * 初始化OkHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse
                                .newBuilder()
                                .body(new FileResponseBody(originalResponse))
                                .build();
                    }
                }).build();

        return okHttpClient;
    }

}
