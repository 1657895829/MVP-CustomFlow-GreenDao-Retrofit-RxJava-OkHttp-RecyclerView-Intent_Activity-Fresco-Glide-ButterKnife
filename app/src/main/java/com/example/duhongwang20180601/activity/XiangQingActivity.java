package com.example.duhongwang20180601.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.duhongwang20180601.R;
import com.example.duhongwang20180601.adapter.PageAdapter;
import com.example.duhongwang20180601.bean.VpBean;
import com.example.duhongwang20180601.custom.MyGroupView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

//菜品详情页，左右滑动展示不同的制作步骤和对应步骤的效果图,小圆点根据图片切换而切换
public class XiangQingActivity extends Activity {
    private ArrayList<VpBean>  list = new ArrayList<>();
    @BindView(R.id.myView)
    MyGroupView myView;
    @BindView(R.id.title)
    TextView XQtitle;
    private String s1;
    private String s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);

        //获取传递的参数,设置显示视图
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ArrayList<String> img = intent.getStringArrayListExtra("img");
        ArrayList<String> step = intent.getStringArrayListExtra("step");

        XQtitle.setText(title+ "  流程图");
         for (int i = 0; i<img.size(); i++){
                s1 = img.get(i);

                for (int j = 0; j<step.size(); j++){
                    System.out.println("数据：" + step.get(j).toString());  //数据已拿到
                    s2 = step.get(j);

                    VpBean bean = new VpBean(s1, s2);
                    list.add(bean);
                    System.out.println("Bean类：" + bean.toString() +"集合：" +list.toString());

                }
            }

        //设置自定义轮播图适配器
        myView.setAdapter(new PageAdapter(list,XiangQingActivity.this));

        //展示自定义轮播图
        myView.tim();
    }











}
