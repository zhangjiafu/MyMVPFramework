package com.zjf.framework.mvp.mymvpframework.ui.login;

import com.zjf.framework.mvp.mymvpframework.data.DataManager;
import com.zjf.framework.mvp.mymvpframework.ui.base.BasePresenter;
import com.zjf.framework.mvp.mymvpframework.ui.base.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/8/9.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager mDataManager, CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mCompositeDisposable);
    }

    @Override
    public void login(String name, String pwd) {

    }
}
