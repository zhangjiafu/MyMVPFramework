package com.zjf.framework.mvp.mymvpframework.ui.login;

import com.zjf.framework.mvp.mymvpframework.ui.base.MvpPresenter;

/**
 * Created by Administrator on 2017/8/9.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void login(String name, String pwd); // 业务逻辑
}
