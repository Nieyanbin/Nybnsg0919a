package com.example.dell.nybnsg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.bean.Erweishangpin;
import com.example.dell.nybnsg.bean.Fenlei;

import java.util.List;

/**
 * Created by dell on 2017/9/5.
 */
public class GirdviewAdapter extends BaseAdapter{
    Context context;
    List<Erweishangpin.DatasBean.ClassListBean> list;


    public GirdviewAdapter(Context context, List<Erweishangpin.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.erweishuju,null);
            holder=new ViewHolder();

            holder.tv=view.findViewById(R.id.tverweiname);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Erweishangpin.DatasBean.ClassListBean item = list.get(i);
        holder.tv.setText(item.getGc_name());

        return view;
    }
    class ViewHolder{
        TextView tv;

    }
}
