package com.hanxuhui.netframework.protocol.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanxuhui on 2016/5/17.
 */
public class Messages extends Response implements Serializable {

    public String pageSize;
    public String messageId;   //请求服务器时用的messageId
    public List<Message> messages;

    public class Message implements Serializable {
        public String messageId;
        public String userId;
        public String mobile;
        public String imageName;
        public String createDate;
        public String messageType;
        public String messageContent;
        public String invitationId;
        public String groupId;
        public String invitationStatus;
        public String sender;
        public String senderId;
        public String senderMobile;
        public String gender;      //消息发送者好友性别
        public String isCheck;     //0-未读 1-已读

        @Override
        public String toString() {
            return "Message{" +
                    "messageId='" + messageId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", imageName='" + imageName + '\'' +
                    ", createDate='" + createDate + '\'' +
                    ", messageType='" + messageType + '\'' +
                    ", messageContent='" + messageContent + '\'' +
                    ", invitationId='" + invitationId + '\'' +
                    ", groupId='" + groupId + '\'' +
                    ", invitationStatus='" + invitationStatus + '\'' +
                    ", sender='" + sender + '\'' +
                    ", senderId='" + senderId + '\'' +
                    ", senderMobile='" + senderMobile + '\'' +
                    ", gender='" + gender + '\'' +
                    ", isCheck='" + isCheck + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Messages{" +
                "pageSize='" + pageSize + '\'' +
                ", messageId='" + messageId + '\'' +
                ", messages=" + messages +
                '}';
    }
}
