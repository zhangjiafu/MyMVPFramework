package com.zjf.framework.mvp.mymvpframework.di.module;

import android.app.Activity;
import android.content.Context;

import com.zjf.framework.mvp.mymvpframework.di.ActivityContext;
import com.zjf.framework.mvp.mymvpframework.di.PerActivity;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginMvpPresenter;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginMvpView;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginPresenter;
import com.zjf.framework.mvp.mymvpframework.ui.main.MainMvpPresenter;
import com.zjf.framework.mvp.mymvpframework.ui.main.MainMvpView;
import com.zjf.framework.mvp.mymvpframework.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/5/25.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable providerCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

}
