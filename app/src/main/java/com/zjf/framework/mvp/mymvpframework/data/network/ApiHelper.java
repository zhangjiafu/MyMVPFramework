package com.zjf.framework.mvp.mymvpframework.data.network;


import com.zjf.framework.mvp.mymvpframework.data.network.entity.KnowWeather;
import com.zjf.framework.mvp.mymvpframework.data.network.model.HttpResponse;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<HttpResponse<KnowWeather>> getKnowWeather(String cityId);


}

