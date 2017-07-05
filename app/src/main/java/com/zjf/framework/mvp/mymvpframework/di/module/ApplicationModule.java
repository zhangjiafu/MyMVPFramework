package com.zjf.framework.mvp.mymvpframework.di.module;

import android.app.Application;
import android.content.Context;

import com.zjf.framework.mvp.mymvpframework.constant.AppConstants;
import com.zjf.framework.mvp.mymvpframework.di.ApplicationContext;
import com.zjf.framework.mvp.mymvpframework.di.DatabaseInfo;
import com.zjf.framework.mvp.mymvpframework.di.PreferenceInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/25.
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName(){
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName(){
        return AppConstants.PREF_NAME;
    }

}
