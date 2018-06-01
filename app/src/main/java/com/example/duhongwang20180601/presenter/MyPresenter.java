package com.example.duhongwang20180601.presenter;

import com.example.duhongwang20180601.base.BasePresenter;
import com.example.duhongwang20180601.bean.Bean;
import com.example.duhongwang20180601.model.MyModel;
import com.example.duhongwang20180601.view.MyView;

/**
 * Created   by   Dewey .
 * presenter层请求数据类
 */
public class MyPresenter extends BasePresenter<MyView> {
    private MyModel myModel;

    public MyPresenter() {
        this.myModel = new MyModel();
    }

    //请求数据的方法
    public void getDataP(String menu){
        myModel.getDataM(menu, new MyView() {
            @Override
            public void success(Bean bean) {
                view.success(bean);
            }

            @Override
            public void failure() {
                view.failure();
            }

        });
    }

}
