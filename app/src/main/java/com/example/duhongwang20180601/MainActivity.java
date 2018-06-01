package com.example.duhongwang20180601;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.duhongwang20180601.activity.ListActivity;
import com.example.duhongwang20180601.adapter.HistoryAdapter;
import com.example.duhongwang20180601.app.MyApplication;
import com.example.duhongwang20180601.bean.SearchDaoBean;
import com.example.duhongwang20180601.custom.FlowLayout;
import com.example.duhongwang20180601.dao.SearchDaoBeanDao;
import org.greenrobot.greendao.query.Query;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
   搜索框页面：
    1. 搜索框输入自己想做的菜名
    2. 自定义流式布局，展示搜索的菜名记录，实现图一效果
    3. 使用GreenDao操作数据库，保存搜索记录，当下次进入搜索页面，能展示记录
    4. 点击清除按钮清除数据库内容
    5. 点击搜索，根据搜索的菜名，请求数据
 */
public class MainActivity extends Activity {
    @BindView(R.id.edit_input)
    EditText editInput;
    @BindView(R.id.search_btn)
    ImageView searchBtn;
    @BindView(R.id.history_recyclerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.id_flowlayout)
    FlowLayout flowlayout;
    private HistoryAdapter historyAdapter;
    List<String> list = new ArrayList<>(); //历史搜索输入数据集合
    private Query<SearchDaoBean> queryDao;  //历史搜索查询数据集合
    private SearchDaoBeanDao dao;
    private String flows[] = {
            "应急启动电源", "餐桌", "粽子散装",
            "智能手表", "摩托车配件", "批发方便面",
            "王中王火腿", "手机", "桶装矿泉水",
            "U盘64G", "机械革命电脑", "洗发水",
            "护发素", "奶粉", "search", "logcat"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //自定义流式布局初始化视图
        initChildViews();

        //设置搜索记录布局视图
        initHistoryResultData();

        //获取数据库实例,把历史记录显示在页面上
        dao = MyApplication.session.getSearchDaoBeanDao();
        queryDao = dao.queryBuilder().orderAsc(SearchDaoBeanDao.Properties.Id).build();
        List<SearchDaoBean> daoBeanList = queryList();
        for (int i = 0; i < daoBeanList.size(); i++) {
            list.add(daoBeanList.get(i).getSelectGoods());
        }

    }

    //流式布局
    public void initChildViews() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //下面为  热搜流式布局子条目间距
        lp.leftMargin = 10;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 10;
        for(int i = 0; i < flows.length; i ++){
            TextView view = new TextView(this);
            view.setText(flows[i]);
            view.setTextSize(21);
            //view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_bg));

            //添加到父View
            flowlayout.addView(view,lp);
        }
    }

    private void initHistoryResultData() {
        //设置搜索结果适配器以及布局管理器
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        historyAdapter = new HistoryAdapter(MainActivity.this, list);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    @OnClick({R.id.search_btn,  R.id.clearbtn , R.id.backJian})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //点击搜索按钮,传递数据RecyclerView显示，并存入数据库
            case R.id.search_btn:
                //判断输入为空情况下
                String string = editInput.getText().toString().trim();
                if (TextUtils.isEmpty(string) || string.length() == 0) {
                    Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("name", string);
                startActivity(intent);

                //保存搜索历史到数据库
                String trim = editInput.getText().toString().trim();
                SearchDaoBean daoBean = new SearchDaoBean(null, "1775", "TheScar", trim);
                dao.insert(daoBean);
                historyAdapter.notifyDataSetChanged();

                break;

            //清空历史搜索集合,，清空数据库，刷新数据
            case R.id.clearbtn:
                list.clear();
                deleteAllData();
                historyAdapter.notifyDataSetChanged();
                break;

            //销毁当前页面
            case R.id.backJian:
                finish();
                break;

            default:
                break;
        }
    }

    //查询全部数据的方法
    private List<SearchDaoBean> queryList() {
        List<SearchDaoBean> daoBeans = queryDao.list();
        return daoBeans;
    }

    //删除所有数据，即清空历史记录
    public void deleteAllData() {
        dao.deleteAll();
    }
}

