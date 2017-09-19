package com.example.dell.nybnsg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.SousuoActivity;

import java.util.List;

/**
 * Created by dell on 2017/9/3.
 */
public class ChazhaojiluAdapter extends BaseAdapter{
    Context context;
    List<String> list;

    public ChazhaojiluAdapter(Context context, List<String> list) {
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
            view= LayoutInflater.from(context).inflate(R.layout.chazhaojilu,null);
            holder=new ViewHolder();
            holder.tv=view.findViewById(R.id.tvjilu);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
       holder.tv.setText(list.get(0));
        return view;
    }
    class ViewHolder{
        TextView tv;
    }
}
