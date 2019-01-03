package com.example.lenovo.fengyue0102.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fengyue0102.R;
import com.example.lenovo.fengyue0102.entity.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {
    private Context context;
    private List<User.Data.TJ.LL> list;

    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
//给集合赋值的方法
    public void setList(List<User.Data.TJ.LL> list) {
        if(list!=null){
        this.list = list;
        }
        notifyDataSetChanged();
    }
//删除的方法
    public void removelist(int position){
        this.list.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User.Data.TJ.LL getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.linear, parent, false);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
       viewHolder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView title,price;
        public ViewHolder(View item) {
            this.img = item.findViewById(R.id.img);
            this.title = item.findViewById(R.id.title);
            this.price = item.findViewById(R.id.price);
            item.setTag(this);
        }

        public void bindData(User.Data.TJ.LL item) {
            title.setText(item.getTitle());
            price.setText(item.getPrice()+"");
            final String[] split = item.getImages().split("!");
            Glide.with(context).load(split[0]).into(this.img);
        }
    }
}
