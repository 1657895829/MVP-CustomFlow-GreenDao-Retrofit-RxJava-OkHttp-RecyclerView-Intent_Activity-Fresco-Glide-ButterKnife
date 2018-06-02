package com.example.duhongwang20180601.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.duhongwang20180601.R;
import com.example.duhongwang20180601.bean.VpBean;
import com.example.duhongwang20180601.custom.CustomBanner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//菜品详情页，左右滑动展示不同的制作步骤和对应步骤的效果图,小圆点根据图片切换而切换
public class XiangQingActivity extends Activity {
    ArrayList<VpBean> stepList = new ArrayList<>();
    List<String> imageList = new ArrayList<>();
    @BindView(R.id.banner)
    CustomBanner banner;
    @BindView(R.id.title)
    TextView XQtitle;
    @BindView(R.id.steps)
    TextView steps;

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

        XQtitle.setText(title + "  流程图");
        for (int i = 0; i < img.size(); i++) {
            String s1 = img.get(i);
            imageList.add(s1);
            for (int j = 0; j < step.size(); j++) {
                System.out.println("数据：" + step.get(j).toString());  //数据已拿到
                String s2 = step.get(j);
                stepList.add(new VpBean(s2));
                steps.setText(stepList.get(j).getDesc());

                System.out.println("list.size()：" + stepList.size());
                System.out.println("Bean类Desc：" + new VpBean(s2).toString() + "集合：" + stepList.toString());
            }
        }


        //解耦,播放图片
        banner.loadData(imageList).display();//构建者模式返回对象本身

    }

}
