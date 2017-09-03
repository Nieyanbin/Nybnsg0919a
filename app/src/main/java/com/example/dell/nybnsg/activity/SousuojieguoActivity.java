package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.nybnsg.R;

public class SousuojieguoActivity extends AppCompatActivity {

    private EditText etss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuojieguo);
        ImageView imgfh= (ImageView) findViewById(R.id.imgfh);
        etss = (EditText) findViewById(R.id.etss);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        etss.setText(name);
        imgfh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
