package com.zjf.framework.mvp.mymvpframework.data.network.service;



import com.zjf.framework.mvp.mymvpframework.data.network.entity.KnowWeather;
import com.zjf.framework.mvp.mymvpframework.data.network.model.HttpResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/2/25
 */
public interface WeatherService {



    /**
     * http://knowweather.duapp.com/v1.0/weather/101010100
     *
     * @param cityId 城市ID
     * @return 天气数据
     */
    @GET("v1.0/weather/{cityId}")
    Observable<HttpResponse<KnowWeather>> getKnowWeather(@Path("cityId") String cityId);


}
