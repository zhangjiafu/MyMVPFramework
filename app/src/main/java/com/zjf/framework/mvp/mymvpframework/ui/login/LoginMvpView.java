package com.zjf.framework.mvp.mymvpframework.ui.login;

import com.zjf.framework.mvp.mymvpframework.ui.base.MvpView;

/**
 * Created by Administrator on 2017/8/9.
 */

public interface LoginMvpView extends MvpView {
    String getUserName();

    String getPwd();

    void loginFail(String failMsg);
}
