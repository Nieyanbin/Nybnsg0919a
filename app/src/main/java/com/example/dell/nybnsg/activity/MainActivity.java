package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dell.nybnsg.R;

public class MainActivity extends AppCompatActivity {
    //使用handler延迟发送
    private int i=2;
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(i==0){
            //跳转页面
            Intent in=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(in);
            finish();
        }
        tv.setText(i+"秒后");
        i--;

        handler.sendEmptyMessageDelayed(99,1000);
    }
};
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tvdjs);
         handler.sendEmptyMessageDelayed(99,1000);
    }
}
