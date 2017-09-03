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
import com.example.dell.nybnsg.bean.Fenlei;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/9/1.
 */
public class FenleiAdapterlist extends BaseAdapter{
    Context context;
    List<Fenlei.DatasBean.ClassListBean> list;
//    private final DisplayImageOptions options;

    public FenleiAdapterlist(Context context, List<Fenlei.DatasBean.ClassListBean> class_list) {
        this.context = context;
        this.list = class_list;
//        options = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .showImageOnLoading(R.mipmap.ic_launcher)
//                .build();
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Fenlei.DatasBean.ClassListBean getItem(int i) {
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
            view= LayoutInflater.from(context).inflate(R.layout.adapterlist,null);
            holder=new ViewHolder();
            holder.img=view.findViewById(R.id.imglist);
            holder.tv=view.findViewById(R.id.tvlist);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Fenlei.DatasBean.ClassListBean item = getItem(i);
        holder.tv.setText(item.getGc_name());
        Glide.with(context).load(list.get(i).getImage()).into(holder.img);
//        ImageLoader.getInstance().displayImage(item.getImage(),holder.img,options);

        return view;
    }
    class ViewHolder{
        TextView tv;
        ImageView img;
    }
}

