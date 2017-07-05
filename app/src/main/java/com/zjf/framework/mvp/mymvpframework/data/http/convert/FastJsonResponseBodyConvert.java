package com.zjf.framework.mvp.mymvpframework.data.http.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Administrator on 2017/7/5.
 */

public class FastJsonResponseBodyConvert<T> implements Converter<ResponseBody, T> {
    private Type type;

    public FastJsonResponseBodyConvert(Type type) {
        this.type = type;
    }

    public T convert(ResponseBody value) throws IOException {
        return JSON.parseObject(value.string(), this.type, new Feature[0]);
    }
}
