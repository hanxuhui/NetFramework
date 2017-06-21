package com.hanxuhui.netframework.main;

import java.util.List;

/**
 * Created by hanxuhui on 2016/7/17.
 */
public interface FindItemsInteractor {

    interface OnCallBackListener {
        void onCallBack(List<String> items);
    }

    void findItems(OnCallBackListener listener);
}
