package com.zjf.framework.mvp.mymvpframework.ui.main;

import com.zjf.framework.mvp.mymvpframework.data.DataManager;
import com.zjf.framework.mvp.mymvpframework.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    @Inject
    public MainPresenter(DataManager mDataManager, CompositeDisposable mCompositeDisposable) {
        super(mDataManager, mCompositeDisposable);
    }
}
