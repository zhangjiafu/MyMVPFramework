package com.zjf.framework.mvp.mymvpframework.ui.base;


import okhttp3.internal.http2.ErrorCode;

/**
 * Created by Administrator on 2017/5/25.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);

    void detachView();

    void handleApiError(ErrorCode error);
}
