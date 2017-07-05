package com.zjf.framework.mvp.mymvpframework.di.module;

import android.app.Activity;
import android.content.Context;

import com.zjf.framework.mvp.mymvpframework.di.ActivityContext;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2017/5/25.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return mActivity;
    }

    @Provides
    Activity provideActivity(){
        return mActivity;
    }

    @Provides
    CompositeDisposable providerCompositeDisposable(){
        return new CompositeDisposable();
    }

}
