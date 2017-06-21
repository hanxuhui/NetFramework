package com.hanxuhui.netframework.login;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

}
