package com.example.duhongwang20180601.base;

/**
 * 针对MVP来封装P层和V层，进行基类抽取和复用
 * 自定义一个Presenter层，持有view层接口
 */
public class BasePresenter<V> {
    // V 相当于V的接口
    public V view;

    /**
     * 自定义Presenter 持有View 的接口
     * @param v
     */
    public void attach(V v){
        this.view = v;
    }

    /**
     * 自定义Presenter 释放持有View的接口， 防止内存泄漏
     */
    public void detach(){
        this.view = null;
    }
}
