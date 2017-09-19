package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.nybnsg.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etzh;
    private EditText etmm;
    private TextView tvzc;
    private Button btndl;
    int i=2;
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(i==0){
            finish();
        }
        i--;
        btndl.setText("登录中…");
        if(i==0){
            btndl.setText("登录成功");
        }

        handler.sendEmptyMessageDelayed(99,1500);
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //查找控件
        etzh = (EditText) findViewById(R.id.etzh);
        etmm = (EditText) findViewById(R.id.etmm);
        btndl = (Button) findViewById(R.id.btndl);
        btndl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessageDelayed(99,1500);
            }
        });
        tvzc = (TextView) findViewById(R.id.tvzc);
        tvzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivity(in);
            }
        });
    }
}
