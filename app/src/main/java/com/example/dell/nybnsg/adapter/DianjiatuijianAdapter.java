package com.example.dell.nybnsg.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.Particulars11Activity;
import com.example.dell.nybnsg.bean.Particular11bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/9/7.
 */
public class DianjiatuijianAdapter extends BaseAdapter {
    Context context;
    List<Particular11bean.DatasBean.GoodsCommendListBean> list;
    private final DisplayImageOptions options;


    public DianjiatuijianAdapter(Context context, List<Particular11bean.DatasBean.GoodsCommendListBean> list) {
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Particular11bean.DatasBean.GoodsCommendListBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.dianjiatuijian,null);
            holder=new ViewHolder();
            holder.img=view.findViewById(R.id.imgdianjiatuijian);
            holder.tvname=view.findViewById(R.id.tvdianjiatuijianname);
            holder.tvmoney=view.findViewById(R.id.tvdianjiatuijianmoney);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Particular11bean.DatasBean.GoodsCommendListBean item = getItem(i);
        holder.tvname.setText(item.getGoods_name());
        holder.tvmoney.setText(item.getGoods_promotion_price());
        ImageLoader.getInstance().displayImage(item.getGoods_image_url(),holder.img,options);
        return view;
    }
    class ViewHolder{
     ImageView img;
        TextView tvname;
        TextView tvmoney;
    }
}
