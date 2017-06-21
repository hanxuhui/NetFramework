package com.hanxuhui.netframework.protocol;

import com.hanxuhui.netframework.protocol.bean.Contact;
import com.hanxuhui.netframework.protocol.bean.Response;
import com.hanxuhui.netframework.protocol.bean.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public interface ApiClient {

    //上海测试环境
    String HOST_HTTP = "http://yoursaddress/";

    //POST请求
    @FormUrlEncoded
    @POST("user/register")
    Observable<Response<User>> login(@Field("mobile") String mobile, @Field("vcode") String password);

    //POST请求
    @POST("user/register")
    Observable<Response<User>> login(@Body User user);

    //POST请求
    @POST("user/register")
    Observable<Response<User>> register(@Body Map<String, String> map);

    //上传图片
    @Multipart
    @POST("user/modifyIcon")
    Observable<Response> modifyIcon(@Part MultipartBody.Part file);

    //上传图片
    @Multipart
    @POST("user/modifyIcon")
    //@Part("key\"; filename=\"fileName.text") 格式不变，只需将fileName.text对应的替换为你想在服务器生成的文件名称
    //icon为上传文件的key值,
    Observable<Response> modifyIcon(@Part("icon\"; filename=\"fileName.text") RequestBody requestBody);

    //上传图片
    @Multipart
    @POST("user/modifyIcon")
    //@Part("key\"; filename=\"fileName.text") 格式不变，只需将fileName.text对应的替换为你想在服务器生成的文件名称
    //icon为上传文件的key值,
    Observable<Response> modifyIcon(@Part("description") RequestBody description, @Part("icon\"; filename=\"fileName.text") RequestBody requestBody);

    //上传多个文件
    @Multipart
    @POST("user/modifyIcon")
    //@Part("key\"; filename=\"fileName.text") 格式不变，只需将fileName.text对应的替换为你想在服务器生成的文件名称
    //icon为上传文件的key值,
    Observable<Response> modifyIcon(@PartMap Map<String, RequestBody> params);

    // 获取联系人列表
    @POST("contact/getContacts")
    Observable<Response<List<Contact>>> getContactList();

    //GET请求
    @GET("user/{id}")
    Observable<Response<User>> getUser(@Path("id") String id);  //相当于user/123456

    //GET请求
    @GET("user")
    Observable<Response<User>> fetchUser(@Query("id") String id);   //相当于user?id="123456"

}
