package com.example.dell.nybnsg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.bean.Particularsbean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/9/7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    Context context;
    List<Particularsbean.DatasBean.GoodsListBean> list;
    private final DisplayImageOptions options;
    private OnitemClick onitemClick;
    public interface OnitemClick{
        public void itemClick(int position);


    }
    public void setItemclick(OnitemClick onitemClick){
        this.onitemClick=onitemClick;
    }

    public RecyclerAdapter(Context context, List<Particularsbean.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .build();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvname.setText(list.get(position).getGoods_name());
        holder.tvmoney.setText(list.get(position).getGoods_price()+"   已售：0件");
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_image_url(),holder.img,options);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onitemClick!=null){
                    onitemClick.itemClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvname;
        TextView tvmoney;
        ImageView img;
         LinearLayout ll;

        public MyViewHolder(View view)
        {
            super(view);
            tvname=view.findViewById(R.id.tvrecyclername);
            tvmoney=view.findViewById(R.id.tvmoney);
            img=view.findViewById(R.id.imgrecycler);
            ll = view.findViewById(R.id.lldianji);
        }
    }
}
