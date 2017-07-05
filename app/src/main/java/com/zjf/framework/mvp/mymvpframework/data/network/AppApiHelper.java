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

package com.zjf.framework.mvp.mymvpframework.data.network;

import com.zjf.framework.mvp.mymvpframework.data.network.model.BlogResponse;
import com.zjf.framework.mvp.mymvpframework.data.network.model.LoginRequest;
import com.zjf.framework.mvp.mymvpframework.data.network.model.LoginResponse;
import com.zjf.framework.mvp.mymvpframework.data.network.model.LogoutResponse;
import com.zjf.framework.mvp.mymvpframework.data.network.model.OpenSourceResponse;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {


    @Override
    public Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                                  request) {
        return null;
    }

    @Override
    public Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                    request) {
        return null;
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request) {
        return null;
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return null;
    }

    @Override
    public Observable<BlogResponse> getBlogApiCall() {
        return null;
    }

    @Override
    public Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return null;
    }
}

