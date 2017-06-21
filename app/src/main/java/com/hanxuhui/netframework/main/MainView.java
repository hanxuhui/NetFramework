package com.hanxuhui.netframework.main;

import java.util.List;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);
}
