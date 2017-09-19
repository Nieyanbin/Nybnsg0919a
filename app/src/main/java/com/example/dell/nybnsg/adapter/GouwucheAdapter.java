package com.example.dell.nybnsg.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.activity.Particulars11Activity;
import com.example.dell.nybnsg.bean.Greendaobean;
import com.example.dell.nybnsg.http.App;
import com.koma.greendao.gen.GreendaobeanDao;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/9/13.
 */
public class GouwucheAdapter extends RecyclerView.Adapter{
    Context context;
    private final GreendaobeanDao greendaobeanDao;
    private final List<Greendaobean> greendaobeen;
    private int i;
    private Float money;
    private int count;
    public GouwucheAdapter(Context context) {
        this.context = context;
        App app = (App) context.getApplicationContext();
        greendaobeanDao = app.getGreendaobeanDao();
        greendaobeen = greendaobeanDao.loadAll();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.gouwuche, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
      if(holder instanceof MyViewHolder){
          ((MyViewHolder) holder).tvname.setText(greendaobeen.get(position).getName());
          Log.e("adasda","asdadadadas"+greendaobeen.get(position).getName());
          ((MyViewHolder) holder).tvmoney.setText(greendaobeen.get(position).getMoney()+"");
          ImageLoader.getInstance().displayImage(greendaobeen.get(position).getUrl(),((MyViewHolder) holder).img);
         ((MyViewHolder) holder).ed.setText(greendaobeen.get(position).getNum());
          Log.e("adasda","num值"+((MyViewHolder) holder).ed.getText().toString());
          ((MyViewHolder) holder).etpopw.setText(((MyViewHolder) holder).ed.getText().toString());
          Log.e("adasda","asdadadadas"+((MyViewHolder) holder).etpopw.getText().toString());
          i = Integer.parseInt(((MyViewHolder) holder).etpopw.getText().toString());
          Log.e("adasda","asdadadadas"+i);
          ((MyViewHolder) holder).zongjie.setText("共"+((MyViewHolder) holder).ed.getText().toString()+"件商品，合计"+((MyViewHolder) holder).tvmoney.getText().toString()+"元");
          //减操作
          ((MyViewHolder) holder).btnjian.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (i >0) {
                      ((MyViewHolder) holder).etpopw.setText(String.valueOf(--i));
                      ((MyViewHolder) holder).ed.setText("x"+((MyViewHolder) holder).etpopw.getText().toString());
                      int count = Integer.parseInt(((MyViewHolder) holder).etpopw.getText().toString());
                      ((MyViewHolder) holder).zongjie.setText("共"+count+"件商品，合计"+(money*count-(count-(count-1)))+"元");
                  }else{
                      Toast.makeText(context, "不能再减少啦", Toast.LENGTH_SHORT).show();
                  }
              }
          });

          //加操作
          ((MyViewHolder) holder).btnjia.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (i < 99) {
                      ((MyViewHolder) holder).etpopw.setText(String.valueOf(++i));
                      ((MyViewHolder) holder).ed.setText("x"+((MyViewHolder) holder).etpopw.getText().toString());
                      money = Float.valueOf(((MyViewHolder) holder).tvmoney.getText().toString());
                      Log.e("钱数","钱数"+ money);
                      count = Integer.parseInt(((MyViewHolder) holder).etpopw.getText().toString());
                      ((MyViewHolder) holder).zongjie.setText("共"+ count +"件商品，合计"+ money * count +"元");
                  }else{
                      Toast.makeText(context, "已经买的够多了", Toast.LENGTH_SHORT).show();
                  }
              }
          });
          //删除操作
          ((MyViewHolder) holder).btnsc.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AlertDialog.Builder ab=new AlertDialog.Builder(context)
                          .setTitle("温馨提示")
                          .setMessage("是否删除该商品");

                  ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {
                          greendaobeanDao.delete(greendaobeen.get(position));
                          greendaobeen.remove(position);
                          notifyDataSetChanged();
                      }
                  });
                         ab .setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
                      @Override
                      public void onClick(DialogInterface dialog, int which) {//响应事件

                          // TODO Auto-generated method stub

                      }
                  }).show();//在按键响应事件中显示此对话框
              }

          });
      }
    }

    @Override
    public int getItemCount() {
        return greendaobeen.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvname;
        TextView tvmoney;
        ImageView img;
        TextView ed;
        Button btnjian;
        EditText etpopw;
        Button btnjia;
        Button btnsc;
        TextView zongjie;
        public MyViewHolder(View view)
        {
            super(view);
            tvname=view.findViewById(R.id.recyclername);
            tvmoney=view.findViewById(R.id.recyclermoney);
            img=view.findViewById(R.id.recyclerimg);
            ed=view.findViewById(R.id.recyclersalenum);
            btnjian=view.findViewById(R.id.btnjian);
            etpopw=view.findViewById(R.id.etpopw);
            btnjia=view.findViewById(R.id.btnjia);
            btnsc=view.findViewById(R.id.btnsc);
            zongjie=view.findViewById(R.id.zongjie);
        }

    }
}
