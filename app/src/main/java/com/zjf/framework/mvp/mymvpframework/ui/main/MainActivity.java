package com.zjf.framework.mvp.mymvpframework.ui.main;

import android.os.Bundle;

import com.zjf.framework.mvp.mymvpframework.R;
import com.zjf.framework.mvp.mymvpframework.ui.base.BaseActivity;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginMvpView;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainMvpPresenter<MainMvpView> mainMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注入
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initData() {

    }



}
