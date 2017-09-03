package com.example.dell.nybnsg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.nybnsg.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment3)
public class Fragment3 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= x.view().inject(this,inflater,container);
        return view;
    }
}
