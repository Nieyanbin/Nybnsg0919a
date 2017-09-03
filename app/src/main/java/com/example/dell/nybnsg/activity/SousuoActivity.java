package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.nybnsg.R;

public class SousuoActivity extends AppCompatActivity {


    private EditText etss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        ImageView imgss= (ImageView) findViewById(R.id.imgsousuoa);
        ImageView imgfh= (ImageView) findViewById(R.id.imgfh);
        etss = (EditText) findViewById(R.id.etss);
        imgss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SousuoActivity.this,SousuojieguoActivity.class);
                in.putExtra("name",etss.getText().toString());
                startActivity(in);

            }
        });
        imgfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
