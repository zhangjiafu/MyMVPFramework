package com.zjf.framework.mvp.mymvpframework.ui.login;

import com.zjf.framework.mvp.mymvpframework.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/9.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {
    @Inject
    LoginPresenter<LoginMvpView> loginPresenter;

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public void loginFail(String failMsg) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }
}
