package com.hanxuhui.netframework.protocol.bean;

/**
 * Created by hanxuhui on 2016/7/11.
 */
public class Member {

    public String userId;
    public String imageName;
    public String gender;
    public String memberName;
    public String longtitude;
    public String latitude;
    public String address;
    public String mobile;
    public String lastLocationTime;
    public String status;

    //附加字段，记录好友状态
    public String hidingDateTime;
    public String hidingHours;
    public boolean isCounting;

    public String groupId;
    public String groupName;
    public String groupStatus; //标识group的状态，如果圈子已不存在，则删除该卡片(推送未正常推过来的情况)

    //推送过来的通知有这个字段
    public String invitationId;

    public String messageId;   //消息id,用于检查是否有新消息，有则在右上角的消息图标上显示红点
    public String times;       //隐身超时时间
    public String limitFlag;   //隐身超时标识,0:未超24时，1：超过24时

    @Override
    public String toString() {
        return "Member{" +
                "userId='" + userId + '\'' +
                ", imageName='" + imageName + '\'' +
                ", gender='" + gender + '\'' +
                ", memberName='" + memberName + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", lastLocationTime='" + lastLocationTime + '\'' +
                ", status='" + status + '\'' +
                ", hidingDateTime='" + hidingDateTime + '\'' +
                ", hidingHours='" + hidingHours + '\'' +
                ", isCounting=" + isCounting +
                ", groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupStatus='" + groupStatus + '\'' +
                ", invitationId='" + invitationId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", times='" + times + '\'' +
                ", limitFlag='" + limitFlag + '\'' +
                '}';
    }
}
