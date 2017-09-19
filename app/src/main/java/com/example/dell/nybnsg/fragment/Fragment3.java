package com.example.dell.nybnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.Particulars11Activity;
import com.example.dell.nybnsg.activity.ParticularsActivity;
import com.example.dell.nybnsg.adapter.GouwucheAdapter;
import com.example.dell.nybnsg.adapter.RecyclerAdapter;
import com.example.dell.nybnsg.bean.Particularsbean;
import com.example.dell.nybnsg.dao.Mysqldao;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.koma.greendao.gen.GreendaobeanDao;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.List;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment3)
public class Fragment3 extends Fragment{

    private Mysqldao dao;
private GouwucheAdapter ad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= x.view().inject(this,inflater,container);
        RecyclerView rv=view.findViewById(R.id.rvrv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        TextView dazongjie=view.findViewById(R.id.dazongjie);
        App app= (App) getActivity().getApplication();

        ad=new GouwucheAdapter(getActivity());
        rv.setAdapter(ad);
        ad.notifyDataSetChanged();

        return view;




    }


}
