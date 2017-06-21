package com.hanxuhui.netframework.protocol.network;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public class CommonResultCode {

    /**
     * 标示操作成功，适用于所有操作
     * */
    public static final int RESULT_SUCCESS = 0;

    /**
     * 验证码不正确
     * */
    public static final int RESULT_VALIDATE_CODE_EXPIRED = 1;

    /**
     * token失效
     * */
    public static final int RESULT_TOKEN_EXPIRED = 2;

    /**
     * 标示操作失败，适用于所有操作
     * */
    public static final int RESULT_FAILED = -1;

    /**
     * 服务器连接失败
     * */
    public static final int RESULT_HOST_CONNECTION_REFUSED = -2;

    /**
     * 服务器连接超时
     * */
    public static final int RESULT_HOST_CONNECTION_TIMEOUT = -3;

    /**
     * 无网络
     * */
    public static final int RESULT_HOST_NO_NETWORK = -4;

    /**
     * 解析数据失败
     * */
    public static final int RESULT_PARSE_DATA_FAILED = -5;

}
