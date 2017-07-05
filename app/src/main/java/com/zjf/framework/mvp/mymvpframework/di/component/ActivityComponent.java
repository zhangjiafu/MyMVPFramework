package com.zjf.framework.mvp.mymvpframework.di.component;

import com.zjf.framework.mvp.mymvpframework.di.PerActivity;
import com.zjf.framework.mvp.mymvpframework.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/5/25.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
}
