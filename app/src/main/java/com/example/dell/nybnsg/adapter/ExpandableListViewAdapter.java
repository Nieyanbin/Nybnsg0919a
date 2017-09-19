package com.example.dell.nybnsg.adapter;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.ParticularsActivity;
import com.example.dell.nybnsg.bean.Erweishangpin;
import com.example.dell.nybnsg.bean.Yiweishangpin;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/9/2.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

   List<Yiweishangpin.DatasBean.ClassListBean> yilist;

    ArrayList<List<Erweishangpin.DatasBean.ClassListBean>> child;
    Context context;

    public ExpandableListViewAdapter(List<Yiweishangpin.DatasBean.ClassListBean> yilist, ArrayList<List<Erweishangpin.DatasBean.ClassListBean>> child, Context context) {
        this.yilist = yilist;
        this.child = child;
        this.context = context;
    }
    /**
     * 获取一级标签总数
     */
    @Override
    public int getGroupCount() {
        return yilist.size();
    }
    /**
     * 获取一级标签下二级标签的总数
     */
    @Override
    public int getChildrenCount(int i) {
        return 1;
    }
    /**
     * 获取一级标签内容
     */
    @Override
    public Object getGroup(int i) {
        return yilist.get(i);
    }
    /**
     * 获取一级标签下二级标签的内容
     */
    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i1);
    }
    /**
     * 获取一级标签的ID
     */
    @Override
    public long getGroupId(int i) {
        return i;
    }
    /**
     * 获取二级标签的ID
     */
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }
    /**
     * 对一级标签进行设置
     */
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View view1=LayoutInflater.from(context).inflate(R.layout.channel_expandablelistview,null);
        TextView tvname=view1.findViewById(R.id.channel_group_name);
        tvname.setText(yilist.get(i).getGc_name());
        return view1;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if(view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.great, null);
            holder = new ViewHolder();
            holder.gv = (GridView) view.findViewById(R.id.gv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        List<Erweishangpin.DatasBean.ClassListBean> classListBeen = child.get(i);
        holder.gv.setAdapter(new GirdviewAdapter(context, classListBeen));
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(context, ParticularsActivity.class);
                context.startActivity(in);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class ViewHolder{
        TextView tv;
        GridView gv;
    }
}
