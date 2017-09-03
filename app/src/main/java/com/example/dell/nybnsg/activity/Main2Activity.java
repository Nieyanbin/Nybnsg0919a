package com.example.dell.nybnsg.activity;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Loginbean;
import com.example.dell.nybnsg.bean.Registerbean;
import com.example.dell.nybnsg.fragment.Fragment1;
import com.example.dell.nybnsg.fragment.Fragment2;
import com.example.dell.nybnsg.fragment.Fragment3;
import com.example.dell.nybnsg.fragment.Fragment4;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.hjm.bottomtabbar.BottomTabBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends AppCompatActivity {
private BottomTabBar btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        x.Ext.init(new Application());
        x.Ext.setDebug(true);
        x.view().inject(this);

        btn=(BottomTabBar)findViewById(R.id.bottom_tab_bar);
        initbtn();
        Httputils.httputils(Main2Activity.this).get("http://169.254.41.208/mobile/index.php?act=goods_class", Loginbean.class, new NetListnter() {
            @Override
            public void onSuccess(Basebean basebean) {
                Loginbean loginbean= (Loginbean) basebean;
            }
        });
        Map<String,String> params=new HashMap<>();
        params.put("username","nieyanbin");
        params.put("password","123");
        params.put("password_confirm","123");
        params.put("email","nyb@qq.com");
        params.put("client","android");
        Httputils.httputils(Main2Activity.this).post("http://169.254.133.48/mobile/index.php?act=login&op=register", params,Registerbean.class, new NetListnter() {
            @Override
            public void onSuccess(Basebean basebean) {
                Registerbean registerbean= (Registerbean) basebean;
            }
        });

    }

    private void initbtn() {
        btn.init(getSupportFragmentManager())
                .addTabItem("首页", R.mipmap.ic_nav_home, Fragment1.class)
                .addTabItem("分类", R.mipmap.ic_nav_class, Fragment2.class)
                .addTabItem("购物车", R.mipmap.ic_nav_cart, Fragment3.class)
                .addTabItem("个人", R.mipmap.ic_nav_user, Fragment4.class);


    }
}
