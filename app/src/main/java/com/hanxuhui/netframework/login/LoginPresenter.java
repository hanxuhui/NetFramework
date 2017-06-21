package com.hanxuhui.netframework.login;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();

}
