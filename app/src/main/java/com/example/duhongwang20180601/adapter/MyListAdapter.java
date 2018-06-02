package com.example.duhongwang20180601.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.duhongwang20180601.R;
import com.example.duhongwang20180601.activity.XiangQingActivity;
import com.example.duhongwang20180601.bean.Bean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created   by   Dewey .
 * RecyclerView展示搜索结果适配器
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListViewHolder> {
    private Context context;
    private List<Bean.ResultBean.DataBean> list;

    public MyListAdapter(Context context) {
        this.context = context;
    }

    //添加数据的方法
    public void addData(List<Bean.ResultBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_adapter, null);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyListViewHolder holder, final int position) {
        holder.draweeView.setImageURI(list.get(position).getSteps().get(0).getImg());
        holder.title.setText(list.get(position).getTitle());
        holder.tags.setText(list.get(position).getTags());

        //点击列表的条目，跳转到图三界面，展示当前菜品的制作流程
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String>  stringArrayList01 = new ArrayList<>();
                ArrayList<String>  stringArrayList02 = new ArrayList<>();
                for (int i = 0 ; i<list.get(position).getSteps().size();i++){
                    stringArrayList01.add(list.get(position).getSteps().get(i).getImg());
                    stringArrayList02.add(list.get(position).getSteps().get(i).getStep());
                }

                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("title",list.get(position).getTitle());       //title
                intent.putStringArrayListExtra("img",stringArrayList01);      //img
                intent.putStringArrayListExtra("step",stringArrayList02);     //step
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView draweeView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.tags)
        TextView tags;
        public MyListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
