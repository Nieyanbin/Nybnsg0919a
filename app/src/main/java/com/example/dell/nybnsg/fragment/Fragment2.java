package com.example.dell.nybnsg.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.ErweimaActivity;
import com.example.dell.nybnsg.activity.ParticularsActivity;
import com.example.dell.nybnsg.adapter.ExpandableListViewAdapter;
import com.example.dell.nybnsg.adapter.FenleiAdapterlist;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Erweishangpin;
import com.example.dell.nybnsg.bean.Fenlei;
import com.example.dell.nybnsg.bean.Loginbean;
import com.example.dell.nybnsg.bean.Yiweishangpin;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment2)
public class Fragment2 extends Fragment {
    @Nullable
    private ExpandableListViewAdapter mExpandableListViewAdapter;
    private FenleiAdapterlist ad;
    private ExpandableListView expandableListView;
    private ListView lv;
    private App app;
    private String gc_id;
    private ViewPager twovp;
    private Fragment[] frags;
    List<Fenlei.DatasBean.ClassListBean> cb;
    private Httputils httputils;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        //控件
        lv = view.findViewById(R.id.lv);
        twovp = (ViewPager) view.findViewById(R.id.twovp);
        ImageView imgsys=view.findViewById(R.id.imgsys);
        imgsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity(), ErweimaActivity.class);
                startActivity(in);
            }
        });
        App app = (App) getActivity().getApplication();
        httputils = app.getHttputils();
        //点击listview切换
        init();
        //获取网络数据
        getData();


        return view;
    }

    private void init() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                twovp.setCurrentItem(position);

            }
        });
    }


    public void getData() {
        OkHttpUtils.get().url("http://169.254.41.208/mobile/index.php?act=goods_class").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {

                Gson g = new Gson();
                Fenlei oneListBean = g.fromJson(response, Fenlei.class);
                List<Fenlei.DatasBean.ClassListBean> class_list = oneListBean.getDatas().getClass_list();
                FenleiAdapterlist adapter = new FenleiAdapterlist(getActivity(), class_list);
                lv.setAdapter(adapter);
                getvp(class_list);
            }
        });

    }

    private void getvp(final List<Fenlei.DatasBean.ClassListBean> class_list) {
        if (class_list != null) {
            frags = new Fragment[class_list.size()];
            cb = class_list;
        }
        twovp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getfrag(position);
            }

            @Override
            public int getCount() {
                return class_list.size();
            }

        });
    }
    private Fragment getfrag(int position) {
        Fragment fg = frags[position];
        if (fg == null) {
            fg = Fragmentfl.getiniturl(cb.get(position).getGc_id());
            frags[position] = fg;
        }
        return fg;
    }
}

