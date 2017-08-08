/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.zjf.framework.mvp.mymvpframework;

import android.app.Application;
import android.util.Log;

import com.zjf.framework.mvp.mymvpframework.data.DataManager;
import com.zjf.framework.mvp.mymvpframework.data.network.ApiConstants;
import com.zjf.framework.mvp.mymvpframework.data.network.configuration.ApiConfiguration;
import com.zjf.framework.mvp.mymvpframework.di.component.ApplicationComponent;
import com.zjf.framework.mvp.mymvpframework.di.component.DaggerApplicationComponent;
import com.zjf.framework.mvp.mymvpframework.di.module.ApplicationModule;

import javax.inject.Inject;



/**
 * Created by janisharali on 27/01/17.
 */

public class MvpApp extends Application {
    private static final String TAG = MvpApp.class.getSimpleName();

    @Inject
    DataManager mDataManager;

//    @Inject
//    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        // TODO: 2017/7/5
//        mApplicationComponent.inject(this);

        //初始化ApiClient
        ApiConfiguration apiConfiguration = ApiConfiguration.builder()
                .dataSourceType(ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW)
                .build();
//        ApiHelper.init(apiConfiguration);
        Log.d(TAG, "onCreate end");

//        AppLogger.init();
//        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }



}
