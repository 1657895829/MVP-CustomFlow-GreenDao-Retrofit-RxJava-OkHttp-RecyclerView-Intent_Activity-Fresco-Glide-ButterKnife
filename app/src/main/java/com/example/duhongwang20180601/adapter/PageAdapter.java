package com.example.duhongwang20180601.adapter;

import android.support.v4.view.PagerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.duhongwang20180601.R;
import com.example.duhongwang20180601.bean.VpBean;
import java.util.ArrayList;

/**
 * Created   by   Dewey .
 * 轮播图适配器
 */
public class PageAdapter extends PagerAdapter {
    private ArrayList<VpBean> list;
    private Context context;

    public PageAdapter(ArrayList list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View inflate = View.inflate(context, R.layout.image, null);
        ImageView img = (ImageView) inflate.findViewById(R.id.img);
        TextView step = (TextView) inflate.findViewById(R.id.step);
        Glide.with(context).load(list.get(position % list.size()).getImg()).into(img);
        step.setText(list.get(position % list.size()).getDesc());

        container.addView(inflate);
        return inflate;
    }
}
