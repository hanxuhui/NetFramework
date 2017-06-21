package com.hanxuhui.netframework.protocol;


import com.hanxuhui.netframework.protocol.bean.Contact;
import com.hanxuhui.netframework.protocol.bean.Locations;
import com.hanxuhui.netframework.protocol.bean.Member;
import com.hanxuhui.netframework.protocol.bean.Messages;
import com.hanxuhui.netframework.protocol.bean.Response;
import com.hanxuhui.netframework.protocol.bean.User;
import com.hanxuhui.netframework.protocol.network.ApiFactory;
import com.hanxuhui.netframework.protocol.network.ErrorTransform;
import com.hanxuhui.netframework.protocol.network.SchedulerTransform;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by hanxuhui on 2016/6/26.
 * 使用rx进行异步处理
 */
public class NetRequest {

    private static ApiClient apiClient = ApiFactory.getApiClientInstance();
    private static ApiGroupClient apiGroupClient = ApiFactory.getApiGroupClientInstance();

    public static void login(String mobile, String password, Subscriber<Response<User>> subscriber) {
        setSubscribe(apiClient.login(mobile, password), subscriber);
    }

    public static void login(User user, Subscriber<Response<User>> subscriber) {
        setSubscribe(apiClient.login(user), subscriber);
    }

    public static void register(Map<String, String> map, Subscriber<Response<User>> subscriber) {
        setSubscribe(apiClient.register(map), subscriber);
    }

    public static void getMessageList(String messageId, String pageSize, Subscriber<Response<Messages>> subscriber) {
        setSubscribe(apiGroupClient.getMessageList(messageId, pageSize), subscriber);
    }

    public static void loadOtherLocation(String groupId, Subscriber<Response<Member>> subscriber) {
        setSubscribe(apiGroupClient.loadOtherLocation(groupId), subscriber);
    }

    public static void uploadLocations(Locations locations, Subscriber<Response> subscriber) {
        setSubscribe(apiGroupClient.uploadLocations(locations), subscriber);
    }

    public static void uploadImage(String filePath, Subscriber<Response> subscriber) {
        // use the FileUtils to get the actual file by uri
        File file = new File(filePath);
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("icon", file.getName(), requestFile);
        setSubscribe(apiClient.modifyIcon(body), subscriber);

        //只需要传入RequestBody可是可以的
//        setSubscribe(apiClient.modifyIcon(requestFile), subscriber);

//        String des = "我是上传文件时附带的一个参数";
//        RequestBody description = RequestBody.create(MediaType.parse("text/plain"),  des);
//        setSubscribe(apiClient.modifyIcon(description, requestFile), subscriber);

//        String des = "我是上传文件时附带的一个参数";
//        setSubscribe(apiClient.modifyIcon(RequestBody.create(null,  des), requestFile), subscriber);

        //多文件上传
//        File file = new File(filePath);
//        RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
//        Map<String, RequestBody> photos = new HashMap<>();
//        photos.put("photo1\"; filename=\"icon1.png", photo);
//        photos.put("photo2\"; filename=\"icon2.png", photo);
//        setSubscribe(apiClient.modifyIcon(photos), subscriber);

    }

    public static void downloadImage(Locations locations, Subscriber<Response> subscriber) {
        setSubscribe(apiGroupClient.uploadLocations(locations), subscriber);
    }

    public static void getContacts(Subscriber<Response<List<Contact>>> subscriber) {
        setSubscribe(apiClient.getContactList(), subscriber);
    }

    /**
     * 设置观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
//                .subscribeOn(Schedulers.io()) //子线程访问网络
//                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .compose(new SchedulerTransform<T>())
                .compose(new ErrorTransform<T>())
                .subscribe(observer);
    }

}
