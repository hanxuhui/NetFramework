package com.hanxuhui.netframework.protocol;

import com.hanxuhui.netframework.protocol.bean.Locations;
import com.hanxuhui.netframework.protocol.bean.Member;
import com.hanxuhui.netframework.protocol.bean.Messages;
import com.hanxuhui.netframework.protocol.bean.Response;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public interface ApiGroupClient {

    //上海测试环境
    String HOST_HTTP = "http://yoursaddress/";

    // 获取消息列表
    @POST("server/message/loadMessages")
    @FormUrlEncoded
    Observable<Response<Messages>> getMessageList(@Field("messageId") String messageId, @Field("pageSize") String pageSize);

    //加载其他人位置
    @POST("server/location/loadOthersLocation/{groupId}")
    Observable<Response<Member>> loadOtherLocation(@Path("groupId") String groupId);

    //POST请求
    @POST("server/location/uploadLocations")
    Observable<Response> uploadLocations(@Body Locations locations);

}
