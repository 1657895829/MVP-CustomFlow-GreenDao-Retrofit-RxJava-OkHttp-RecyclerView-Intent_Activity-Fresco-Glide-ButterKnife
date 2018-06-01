package com.example.duhongwang20180601.utils;

import com.example.duhongwang20180601.bean.Bean;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 封装的网络数据接口请求类
 */

public interface APIServiceInterface {
    //搜索的接口,集合传参
    //http://apis.juhe.cn/cook/query?key=ee24b2a77b00a217214bf726fd0ca8ea&menu=西红柿
    @GET("/cook/query")
    Call<Bean> getEdit(@QueryMap Map<String, String> map);


    //get请求方式，传入网址url，Map集合传参，使用Observer被观察者订阅执行
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, String> map);

    //get请求方式，传入网址url，不传参，直接使用Observer被观察者订阅执行
    @GET
    Observable<String> get(@Url String url);

    //post请求方式，传入网址url，Map集合传参，使用Observer被观察者订阅执行
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, String> map);

}
