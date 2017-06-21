package com.hanxuhui.netframework.protocol.bean;

/**
 * Created by hanxuhui on 2016/6/10.
 */
public class Contact {

    public String id;
    public String contactName;
    public String contactMobile;
    public String contactPwd;
    public String relation;
    public String isContacted;  //0为未通知
    public String isDeleted;    //1为失效

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                ", contactPwd='" + contactPwd + '\'' +
                ", relation='" + relation + '\'' +
                ", isContacted='" + isContacted + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
