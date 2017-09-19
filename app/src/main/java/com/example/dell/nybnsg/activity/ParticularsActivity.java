package com.example.dell.nybnsg.activity;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.RecyclerAdapter;
import com.example.dell.nybnsg.bean.Particularsbean;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;

import java.util.List;

public class ParticularsActivity extends AppCompatActivity {
private RecyclerAdapter ad;
    private List<Particularsbean.DatasBean.GoodsListBean> list;
    private Httputils httputils;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        //查找控件
        rv = (RecyclerView) findViewById(R.id.rv);
        EditText etxqss= (EditText) findViewById(R.id.etxqss);
        ImageView imgss= (ImageView) findViewById(R.id.imgsousuoa);
        ImageView imgfh= (ImageView) findViewById(R.id.imgfh);
        TextView tvpaixu= (TextView) findViewById(R.id.tvpaixu);
        App app = (App) getApplication();
        httputils = app.getHttputils();
        //分割线
        rv.addItemDecoration(new DividerItemDecoration(
                ParticularsActivity.this, DividerItemDecoration.VERTICAL));
        jiexi();
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


    }

    private void jiexi() {
        httputils.get("http://169.254.41.208/mobile/index.php?act=goods&op=goods_list&page=100", Particularsbean.class, new NetListnter() {
            @Override
            public void onSuccess(Object basebean) {
                   Particularsbean particularsbean= (Particularsbean) basebean;
                final List<Particularsbean.DatasBean.GoodsListBean> goods_commend_list = particularsbean.getDatas().getGoods_list();
                ad=new RecyclerAdapter(ParticularsActivity.this,goods_commend_list);
                rv.setAdapter(ad);
                ad.setItemclick(new RecyclerAdapter.OnitemClick() {
                    @Override
                    public void itemClick(int position) {
                        String goods_id = goods_commend_list.get(position).getGoods_id();
                        Intent in=new Intent(ParticularsActivity.this,Particulars11Activity.class);
                        in.putExtra("goods_id",goods_id);
                        startActivity(in);
                    }
                });
            }
        });
    }
}
