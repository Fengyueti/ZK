package com.example.rk7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MAdapter extends BaseAdapter {
    private Context context;
    private List<User.Data.Car> list;

    public MAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<User.Data.Car> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void setadd(List<User.Data.Car> list1) {
        if(list!=null) {
         list.addAll(list1);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User.Data.Car getItem(int position) {
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
            viewHolder= new ViewHolder(convertView);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView title;

        public ViewHolder(View item) {
            this.title = item.findViewById(R.id.title);
            item.setTag(this);
        }

        public void bindData(User.Data.Car item) {
            title.setText(item.getGoods_name());
        }
    }
}
