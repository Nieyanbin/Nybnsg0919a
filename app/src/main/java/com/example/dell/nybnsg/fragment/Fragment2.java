package com.example.dell.nybnsg.fragment;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.ExpandableListViewAdapter;
import com.example.dell.nybnsg.adapter.FenleiAdapterlist;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Fenlei;
import com.example.dell.nybnsg.bean.Loginbean;
import com.example.dell.nybnsg.bean.Yiweishangpin;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.List;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment2)
public class Fragment2 extends Fragment{
    @Nullable
private ExpandableListViewAdapter mExpandableListViewAdapter;
    private FenleiAdapterlist ad;
    private ListView lv;
    private ExpandableListView mExpandableListView;
    private App app;
    private List<Fenlei.DatasBean.ClassListBean> class_list;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view= x.view().inject(this,inflater,container);
        lv = view.findViewById(R.id.lv);
        app = (App) getActivity().getApplication();
        Httputils httputils = app.getHttputils();
       httputils.get("http://169.254.41.208/mobile/index.php?act=goods_class", Fenlei.class, new NetListnter() {



           @Override
           public void onSuccess(Basebean basebean) {
             Fenlei fenlei= (Fenlei) basebean;
               Log.d("dasdadada","驱蚊器请我骑单车"+fenlei.getDatas().getClass_list().toString());
               class_list = fenlei.getDatas().getClass_list();
               ad=new FenleiAdapterlist(getActivity(), class_list);
               lv.setAdapter(ad);
           }

       });
        mExpandableListView = view.findViewById(R.id.expandableListView);

        initelv();
        yiweishuzu();
        return view;
    }

    private void yiweishuzu() {
        Httputils httputils = app.getHttputils();


//        httputils.get(, Fenlei.class, new NetListnter() {
//            @Override
//            public void onSuccess(Basebean basebean) {
//                Fenlei fenlei= (Fenlei) basebean;
//                Log.d("dasdadada","驱蚊器请我骑单车啊啊啊啊啊啊啊啊啊啊啊啊"+fenlei.getDatas().getClass_list().toString());
//            }
//        });

    }

    private void initelv() {

        mExpandableListViewAdapter = new ExpandableListViewAdapter(getActivity());

        mExpandableListView.setAdapter(mExpandableListViewAdapter);   //设置它的adapter
        mExpandableListView.expandGroup(0);   //默认打开第一条item

        //设置父item的点击事件
        mExpandableListView
                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent,
                                                View v, int groupPosition, long id) {
                        return false;
                    }
                });

        //设置子item的点击事件
        mExpandableListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v, int groupPosition, int childPosition,
                                                long id) {

                        return false;
                    }
                });

    }

}

