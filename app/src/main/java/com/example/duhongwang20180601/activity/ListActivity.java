package com.example.duhongwang20180601.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.duhongwang20180601.R;
import com.example.duhongwang20180601.adapter.MyListAdapter;
import com.example.duhongwang20180601.base.BaseMvpActivity;
import com.example.duhongwang20180601.bean.Bean;
import com.example.duhongwang20180601.presenter.MyPresenter;
import com.example.duhongwang20180601.view.MyView;
import butterknife.BindView;
import butterknife.ButterKnife;

//请求数据，使用RecyclerView展示列表，使用Fresco加载图片
public class ListActivity extends BaseMvpActivity<MyView, MyPresenter> implements MyView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private MyListAdapter adapter;

    @Override
    public MyPresenter initPresenter() {
        return new MyPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        //获取传递的数据
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        p.getDataP(name);

        //设置搜索结果布局视图
        initSearchResultData();
    }

    private void initSearchResultData() {
        //设置搜索结果适配器以及布局管理器,添加分割线
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new DividerItemDecoration(ListActivity.this,DividerItemDecoration.VERTICAL));
        adapter = new MyListAdapter(ListActivity.this);
    }

    @Override
    public void success(Bean bean) {
        //添加数据
        if (adapter != null) {
            adapter.addData(bean.getResult().getData());
            recyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void failure() {

    }

}
