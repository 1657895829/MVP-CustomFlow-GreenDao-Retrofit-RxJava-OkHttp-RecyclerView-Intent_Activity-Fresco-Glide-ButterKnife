package com.example.duhongwang20180601.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * 2.针对MVP来封装P层和V层，进行基类抽取和复用
 * Activity抽象类,持有View层和Presenter层
 * @param <V>  表示持有的View层
 * @param <T>  表示持有的Presenter层
 */
public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends Activity {

    //声明持有的p层
    public T p;

    public abstract T initPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        p = initPresenter();
    }

    //视图运行获取焦点时，连接view层
    @Override
    protected void onResume() {
        super.onResume();
        p.attach((V) this);
    }

    //视图销毁时释放内存
    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.detach();
    }
}