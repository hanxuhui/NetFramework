package com.hanxuhui.netframework.main;

import java.util.List;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public class MainPresenterImpl implements MainPresenter, FindItemsInteractor. OnCallBackListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        findItemsInteractor = new FindItemsInteractorImpl();
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(this);
    }

    @Override
    public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onCallBack(List<String> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }
}
