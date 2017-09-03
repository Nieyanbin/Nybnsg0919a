package com.example.dell.nybnsg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.LoginActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by dell on 2017/8/31.
 */
@ContentView(R.layout.fragment4)
public class Fragment4 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view= x.view().inject(this,inflater,container);
        ImageView imgtx=view.findViewById(R.id.imgtx);
        imgtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
            }
        });
        return view;
    }
}
