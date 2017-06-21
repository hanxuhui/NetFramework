package com.hanxuhui.netframework.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by hanxuhui on 2016/7/6.
 */
public class RxBus {

    private static volatile RxBus mInstance;
    private final Subject rxBus;

    private RxBus() {
        rxBus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        RxBus rxBus = mInstance;
        if(rxBus == null) {
            synchronized (RxBus.class) {
                rxBus = mInstance;
                if(rxBus == null) {
                    rxBus = new RxBus();
                    mInstance = rxBus;
                }
            }
        }
        return rxBus;
    }


    /**
     * 发送一个新事件
     * @param event
     */
    public void send(Object event) {
        rxBus.onNext(event);
    }

    /**
     * 返回特定类型的被观察者
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return rxBus.ofType(eventType);
    }

    public boolean hasObservers() {
        return rxBus.hasObservers();
    }

}
