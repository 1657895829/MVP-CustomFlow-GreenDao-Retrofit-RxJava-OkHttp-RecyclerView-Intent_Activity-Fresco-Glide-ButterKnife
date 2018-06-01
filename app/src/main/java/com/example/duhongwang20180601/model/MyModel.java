package com.example.duhongwang20180601.model;

import com.example.duhongwang20180601.bean.Bean;
import com.example.duhongwang20180601.utils.APIGet_Post_Factory;
import com.example.duhongwang20180601.utils.APIServiceInterface;
import com.example.duhongwang20180601.utils.AbstractObserver;
import com.example.duhongwang20180601.utils.RetrofitUtils;
import com.example.duhongwang20180601.view.MyView;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created   by   Dewey .
 * model层请求数据
 * http://apis.juhe.cn/cook/query?key=ee24b2a77b00a217214bf726fd0ca8ea&menu=西红柿
 */
public class MyModel {
    //搜索数据方法
    public void getDataM(String menu, final MyView myView){
        //使用Retrofit结合RxJava，okhttp封装类的单例模式,集合传参
        APIServiceInterface request = RetrofitUtils.getInstance();
        Map<String,String>  map = new HashMap<>();
        map.put("key","ee24b2a77b00a217214bf726fd0ca8ea");
        map.put("menu",menu);

        request.getEdit(map).enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean bean = response.body();
                myView.success(bean);
                System.out.println("model数据："+bean.getResult().getData().toString());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                myView.failure();
            }
        });

        APIGet_Post_Factory.getInstance().get("/cook/query?key=ee24b2a77b00a217214bf726fd0ca8ea&menu=西红柿&rn=10&pn=3", map, new AbstractObserver<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                myView.success(bean);
            }

            @Override
            public void onFailure(Exception e) {
                myView.failure();
            }
        });
    }
}
