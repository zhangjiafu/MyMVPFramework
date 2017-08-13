package com.zjf.framework.mvp.mymvpframework.di.component;

import com.zjf.framework.mvp.mymvpframework.ui.main.MainActivity;
import com.zjf.framework.mvp.mymvpframework.di.PerActivity;
import com.zjf.framework.mvp.mymvpframework.di.module.ActivityModule;
import com.zjf.framework.mvp.mymvpframework.ui.login.LoginActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/5/25.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(LoginActivity activity);

//    void inject(SplashActivity activity);

//    void inject(AboutFragment fragment);
}
