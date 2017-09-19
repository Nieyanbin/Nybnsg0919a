package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.adapter.ChazhaojiluAdapter;

import java.util.List;

public class SousuoActivity extends AppCompatActivity {


    private EditText etssss;
private ChazhaojiluAdapter ad;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        //查找控件
        ImageView imgss= (ImageView) findViewById(R.id.imgsousuoa);
        ImageView imgfh= (ImageView) findViewById(R.id.imgfh);
        ListView lv= (ListView) findViewById(R.id.lv);

        etssss = (EditText) findViewById(R.id.etssss);

        ad=new ChazhaojiluAdapter(this,list);
        lv.setAdapter(ad);
        overridePendingTransition(R.anim.pingyi, R.anim.pingyichu);



        //跳转
        imgss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etssss.getText().toString().trim().equals("")){
                    Toast.makeText(SousuoActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    Intent in=new Intent(SousuoActivity.this,SousuojieguoActivity.class);
                    in.putExtra("name",etssss.getText().toString());
                    startActivity(in);
                    etssss.setText("");
                }


            }
        });
        //返回
        imgfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
