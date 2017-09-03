package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dell.nybnsg.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //查找控件
        TextView tvzc= (TextView) findViewById(R.id.tvzc);
        tvzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(LoginActivity.this,ZhuceActivity.class);
                startActivity(in);
            }
        });
    }
}
