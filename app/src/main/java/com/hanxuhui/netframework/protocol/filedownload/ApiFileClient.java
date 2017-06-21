package com.hanxuhui.netframework.protocol.filedownload;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by hanxuhui on 2016/7/14.
 */
public interface ApiFileClient {

    /**
     * 下载文件
     *
     * @param url
     * @return
     */
    @GET
    Call<ResponseBody> downloadFile(@Url String url);


    /**
     * 下载大文件
     *
     * @param url
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadBigFile(@Url String url);

}
