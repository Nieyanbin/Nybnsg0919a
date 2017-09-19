package com.example.dell.nybnsg.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.ExpandableListViewAdapter;
import com.example.dell.nybnsg.bean.Erweishangpin;
import com.example.dell.nybnsg.bean.Fenlei;
import com.example.dell.nybnsg.bean.Yiweishangpin;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/9/5.
 */

public class Fragmentfl extends Fragment{
    private ExpandableListView mExpandableListView;
    private ExpandableListViewAdapter mExpandableListViewAdapter;
    private String gc_id;
    private String twourl = "http://169.254.41.208/mobile/index.php?act=goods_class&gc_id=";
    private Object two;

    public static Fragmentfl getiniturl(String gc_id){
        Fragmentfl twoFragment = new Fragmentfl();
        Bundle bundle = new Bundle();
        bundle.putString("gc_id",gc_id);
        twoFragment.setArguments(bundle);
        return twoFragment;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.erjibiao, container, false);
        mExpandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        gc_id = getArguments().getString("gc_id");

        getData();
        gettwo();
        return v;
    }

    public void getData() {
        x.http().get(new RequestParams(twourl+gc_id), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                final Gson g = new Gson();
                Yiweishangpin twoBean = g.fromJson(result, Yiweishangpin.class);
                final List<Yiweishangpin.DatasBean.ClassListBean> class_list = twoBean.getDatas().getClass_list();
                final ArrayList<List<Erweishangpin.DatasBean.ClassListBean>> arr = new ArrayList<List<Erweishangpin.DatasBean.ClassListBean>>();
                arr.clear();
                for (Yiweishangpin.DatasBean.ClassListBean cb: class_list) {
                    final String gcId = cb.getGc_id();
                    x.http().get(new RequestParams(twourl + gcId), new CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {

                            Gson g1 = new Gson();
                            Erweishangpin threeBean = g1.fromJson(result, Erweishangpin.class);
                            List<Erweishangpin.DatasBean.ClassListBean> class_list2 = threeBean.getDatas().getClass_list();
                            arr.add(class_list2);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });

                }
                mExpandableListViewAdapter = new ExpandableListViewAdapter( class_list, arr,getActivity());
                mExpandableListView.setAdapter(mExpandableListViewAdapter);   //设置它的adapter


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void gettwo() {
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

                        Toast.makeText(getActivity(), "这是第"+childPosition+"条", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
    }
}
