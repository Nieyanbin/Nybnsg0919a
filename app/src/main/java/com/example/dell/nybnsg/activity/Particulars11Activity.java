package com.example.dell.nybnsg.activity;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.DianjiatuijianAdapter;
import com.example.dell.nybnsg.adapter.GouwucheAdapter;
import com.example.dell.nybnsg.bean.Greendaobean;
import com.example.dell.nybnsg.bean.Particular11bean;
import com.example.dell.nybnsg.dao.Mysqldao;
import com.example.dell.nybnsg.fragment.Fragment3;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.example.dell.nybnsg.utils.GlideImageLoader;
import com.example.dell.nybnsg.utils.MyView;
import com.koma.greendao.gen.DaoMaster;
import com.koma.greendao.gen.DaoSession;
import com.koma.greendao.gen.GreendaobeanDao;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;


import java.util.ArrayList;
import java.util.List;

public class Particulars11Activity extends AppCompatActivity{

    private ListView lv;
    private TextView tvllsname;
    private TextView tvllsgj;
    private TextView tvllsmoney;
    private TextView tvxiaoliang;
    private TextView tvquanguo;
    private TextView tvyouhuo;
    private TextView tvmianyunfei;
    private TextView tvspjs;
    private Httputils httputils;
    private String goods_id;
    private ScrollView sll;
    private TextView tvjiarugwc;
    private Button btngouwuche;
    private Button btngoumai;
    private Button btnjian;
    private Button btnjia;
    private EditText etpopw;
    private int i;
    private int max = 99;
    private final int MIN =0;
    private int n;
    private ImageView imgpopw;
    private TextView tvpopwname;
    private TextView tvpopwmoney;
    private TextView tvpopwkucun;
    private Particular11bean basebean1;
    private Mysqldao dao;
    private String popwname;
    private String popwprice;
    private List<String> spec_image;
    private float ff;
    private String s;
    private Banner banner;
    private SQLiteDatabase db;
    private int num;
    private Float f;
    private String s1;
    private DaoMaster.DevOpenHelper helper;
    private App app;
private GouwucheAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars11);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        banner = (Banner) findViewById(R.id.vp);
        lv = (ListView) findViewById(R.id.lv);
        sll = (ScrollView) findViewById(R.id.sll);
        tvllsname = (TextView) findViewById(R.id.tvllsname);
        tvllsgj = (TextView) findViewById(R.id.tvllsgj);
        tvllsmoney = (TextView) findViewById(R.id.tvllsmoney);
        tvxiaoliang = (TextView) findViewById(R.id.tvxiaoliang);
        tvquanguo = (TextView) findViewById(R.id.tvquanguo);
        tvyouhuo = (TextView) findViewById(R.id.tvyouhuo);
        tvmianyunfei = (TextView) findViewById(R.id.tvmianyunfei);
        tvspjs = (TextView) findViewById(R.id.tvspjs);
        LinearLayout llgwc= (LinearLayout) findViewById(R.id.llgouwuche);

        LinearLayout xiangqing= (LinearLayout) findViewById(R.id.xiangqingll);
        tvspjs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent in=new Intent(Particulars11Activity.this,WebviewActivity.class);
                in.putExtra("goods_id",goods_id);
                startActivity(in);
            }
        });
        tvjiarugwc = (TextView) findViewById(R.id.tvjiarugwc);
        llgwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现动画
                showPopwindow();
            }
        });
        TextView tvgoumai= (TextView) findViewById(R.id.tvgoumai);

        app = (App) getApplication();
        httputils = app.getHttputils();
      //解析
        jiexi();
        //无线轮播
        initView();
    }
//添加数据库
    private void shujuku() {
        GreendaobeanDao greendaobeanDao = app.getGreendaobeanDao();
        Greendaobean greendaobean=new Greendaobean();
        greendaobean.setName(popwname);
        greendaobean.setMoney(f);
        greendaobean.setNum(s1);
        greendaobean.setUrl(s);
        greendaobeanDao.insert(greendaobean);
        Toast.makeText(Particulars11Activity.this,"添加购物车",Toast.LENGTH_SHORT).show();
    }

    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Particulars11Activity.this.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow, null);
        imgpopw = view.findViewById(R.id.imgpopw);
        tvpopwname = view.findViewById(R.id.tvpopwname);
        tvpopwmoney = view.findViewById(R.id.tvpopwmoney);
        tvpopwkucun = view.findViewById(R.id.tvpopwkucun);

        btngouwuche =view.findViewById(R.id.btngouwuche);
        btngoumai = view.findViewById(R.id.btngoumai);
        btnjian = view.findViewById(R.id.btnjian);
        btnjia = view.findViewById(R.id.btnjia);
        etpopw = view.findViewById(R.id.etpopw);


        popwname = basebean1.getDatas().getGoods_info().getGoods_name();
        tvpopwname.setText(popwname);
        popwprice = basebean1.getDatas().getGoods_info().getGoods_price();


        tvpopwmoney.setText("￥"+ popwprice);


        f = Float.valueOf(popwprice);
        Log.e("dadasd","dasdasd"+f);
        spec_image = basebean1.getDatas().getSpec_image();
        s = spec_image.get(0);
        Log.e("dasdadad","adasdasdasda"+s.toString());
        ImageLoader.getInstance().displayImage(s,imgpopw);

        int goods_click = basebean1.getDatas().getGoods_info().getGoods_click();
        tvpopwkucun.setText(String.valueOf("库存："+goods_click+"件"));

        i = Integer.parseInt(etpopw.getText().toString().trim());
        Log.e("dadadadadad","adasdadadadada"+i);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        window.setOutsideTouchable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(Particulars11Activity.this.findViewById(R.id.tvjiarugwc),
                Gravity.BOTTOM, 0, 0);

        // 购物车点击
        btngouwuche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                shujuku();
            }
        });
        //立即购买点击
        btngoumai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(Particulars11Activity.this,"啦啦",Toast.LENGTH_SHORT).show();
            }
        });
        //减操作
        btnjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (i>0) {
                        etpopw.setText(String.valueOf(--i));
                    }else{
                        Toast.makeText(Particulars11Activity.this, "不能再减少啦", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        //加操作
        btnjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (i < max) {
                        etpopw.setText(String.valueOf(++i));
                    }else{
                        Toast.makeText(Particulars11Activity.this, "已经买的够多了", Toast.LENGTH_SHORT).show();
                    }




            }
        });

        s1 = etpopw.getText().toString();
        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });


}

    private void initView() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        //        banner.start();
    }

    private void jiexi() {
       httputils.get("http://169.254.41.208/mobile/index.php?act=goods&op=goods_detail&goods_id="+goods_id, Particular11bean.class, new NetListnter() {



           @Override
           public void onSuccess(Object basebean) {

               basebean1 = (Particular11bean) basebean;
               String goods_name = basebean1.getDatas().getGoods_info().getGoods_name();
               tvllsname.setText(goods_name);
               String goods_jingle = basebean1.getDatas().getGoods_info().getGoods_jingle();
               tvllsgj.setText(goods_jingle);
               String goods_price = basebean1.getDatas().getGoods_info().getGoods_price();
               tvllsmoney.setText(goods_price);
               String goods_promotion_type = basebean1.getDatas().getGoods_info().getGoods_promotion_type();
               tvxiaoliang.setText("销量："+goods_promotion_type+"件");
               String area_name = basebean1.getDatas().getGoods_hair_info().getArea_name();
               tvquanguo.setText(area_name);
               String if_store_cn = basebean1.getDatas().getGoods_hair_info().getIf_store_cn();
               tvyouhuo.setText(if_store_cn);
               String content = basebean1.getDatas().getGoods_hair_info().getContent();
               tvmianyunfei.setText(content);
               String goods_image = basebean1.getDatas().getGoods_image();
               List<String> lists=new ArrayList<String>();
               lists.add(goods_image);
               banner.setImages(lists);
               banner.start();
               //得到数据
               List<Particular11bean.DatasBean.GoodsCommendListBean> goods_commend_list = basebean1.getDatas().getGoods_commend_list();

               //添加适配器
               DianjiatuijianAdapter dad=new DianjiatuijianAdapter(Particulars11Activity.this,goods_commend_list);

               lv.setAdapter(dad);


           }
       });
    }


}
