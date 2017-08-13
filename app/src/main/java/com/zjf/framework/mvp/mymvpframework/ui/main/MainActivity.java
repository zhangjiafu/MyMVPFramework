package com.zjf.framework.mvp.mymvpframework;

import android.os.Bundle;

import com.zjf.framework.mvp.mymvpframework.ui.base.BaseActivity;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginMvpView;


public class MainActivity extends BaseActivity implements LoginMvpView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {

    }


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
}
