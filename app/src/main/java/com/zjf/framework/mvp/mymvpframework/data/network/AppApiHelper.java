package com.zjf.framework.mvp.mymvpframework.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zjf.framework.mvp.mymvpframework.BuildConfig;
import com.zjf.framework.mvp.mymvpframework.data.network.configuration.ApiConfiguration;
import com.zjf.framework.mvp.mymvpframework.data.network.entity.KnowWeather;
import com.zjf.framework.mvp.mymvpframework.data.network.model.HttpResponse;
import com.zjf.framework.mvp.mymvpframework.data.network.service.WeatherService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/8.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private static final int DEFAULT_TIMEOUT = 5;

    private static WeatherService weatherService;

    public static ApiConfiguration configuration;

    @Inject
    public AppApiHelper(ApiConfiguration apiConfiguration){
        configuration = apiConfiguration;
        switch (configuration.getDataSourceType()) {
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW:
                weatherService = createRetrofit(ApiConstants.KNOW_WEATHER_API_HOST, WeatherService.class);
                break;
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_MI:
                weatherService = createRetrofit(ApiConstants.MI_WEATHER_API_HOST, WeatherService.class);
                break;
        }
    }


    private static OkHttpClient createClient(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor)/*.addNetworkInterceptor(new StethoInterceptor())*/;
        }
        return builder.build();
    }

    private static  <T> T createRetrofit(String baseUrl, Class<T> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())        //Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//                .addConverterFactory(FastJsonConverterFactory.create())  //FastJson解析
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        return retrofit.create(service);
    }


    @Override
    public Observable<HttpResponse<KnowWeather>> getKnowWeather(String cityId) {
        return weatherService.getKnowWeather(cityId);
    }
}
