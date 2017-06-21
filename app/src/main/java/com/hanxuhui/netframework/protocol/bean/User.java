package com.hanxuhui.netframework.protocol.bean;

/**
 * Created by hanxuhui on 2016/6/26.
 */
public class User {

    public String mobile;
    public String username;
    public String password;
    public String userId;
    public String imageName; //头像名称
    public String vcode;
    public String token;    //设备token
    public String height;   //身高
    public String weight;   //体重
    public String age;      //年龄
    public String gender;   //性别

    @Override
    public String toString() {
        return "User{" +
                "mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", imageName='" + imageName + '\'' +
                ", vcode='" + vcode + '\'' +
                ", token='" + token + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
