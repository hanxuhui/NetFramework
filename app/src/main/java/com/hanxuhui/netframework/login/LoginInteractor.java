package com.hanxuhui.netframework.login;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public interface LoginInteractor {

    interface OnLoginCallBackListener {

        void onUsernameError();
        void onPasswordError();
        void onSuccess();

    }

    void login(String username, String password, OnLoginCallBackListener listener);

}
