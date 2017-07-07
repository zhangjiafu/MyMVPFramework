package com.zjf.framework.mvp.mymvpframework.data.http;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zjf.framework.mvp.mymvpframework.BuildConfig;
import com.zjf.framework.mvp.mymvpframework.data.http.configuration.ApiConfiguration;
import com.zjf.framework.mvp.mymvpframework.data.http.service.WeatherService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static final int DEFAULT_TIMEOUT = 5;

    public static WeatherService weatherService;

    public static ApiConfiguration configuration;

    public static void init(ApiConfiguration apiConfiguration) {

        configuration = apiConfiguration;
        String weatherApiHost;
        switch (configuration.getDataSourceType()) {
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_KNOW:
                weatherApiHost = ApiConstants.KNOW_WEATHER_API_HOST;
                weatherService = createRetrofit(weatherApiHost, WeatherService.class);
                break;
            case ApiConstants.WEATHER_DATA_SOURCE_TYPE_MI:
                weatherApiHost = ApiConstants.MI_WEATHER_API_HOST;
                weatherService = createRetrofit(weatherApiHost, WeatherService.class);
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



}

