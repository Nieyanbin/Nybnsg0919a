package com.example.dell.nybnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.SousuoActivity;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Loginbean;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment1)
public class Fragment1 extends Fragment{

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = x.view().inject(this,inflater,container);
        Button btnss=v.findViewById(R.id.btnss);

        btnss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.pingyi);
//                v.startAnimation(animation);
                startActivity(new Intent(getActivity(), SousuoActivity.class));

            }


        });
        return v;
    }
}
