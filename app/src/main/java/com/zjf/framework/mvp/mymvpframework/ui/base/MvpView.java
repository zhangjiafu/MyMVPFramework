package com.zjf.framework.mvp.mymvpframework.ui.base;

import android.support.annotation.StringRes;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    /**显示加载框 */
    void showLoading();

    /**隐藏加载框 */
    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    /** 网络是否连接 */
    boolean isNetworkConnected();

    /** 隐藏键盘 */
    void hideKeyboard();
}
