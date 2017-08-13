package com.zjf.framework.mvp.mymvpframework.di.component;

import android.app.Application;
import android.content.Context;

import com.zjf.framework.mvp.mymvpframework.MvpApp;
import com.zjf.framework.mvp.mymvpframework.di.ApplicationContext;
import com.zjf.framework.mvp.mymvpframework.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/5/25.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MvpApp app);
//
//    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();
}
