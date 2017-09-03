package com.example.dell.nybnsg.activity;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Shujuku;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhuceActivity extends AppCompatActivity {

    private EditText etzh;
    private EditText etmm;
    private EditText etzcmm;
    private EditText etyx;
    private DbManager db1;
    private App app;
private Map<String,String> map=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        x.view().inject(this);
        etzh = (EditText) findViewById(R.id.etzh);
        etmm = (EditText) findViewById(R.id.etmm);
        etzcmm = (EditText) findViewById(R.id.etzcmm);
        etyx = (EditText) findViewById(R.id.etyx);
        map.put("username",etzh.getText().toString());
        map.put("password",etmm.getText().toString());
        map.put("password_confirm",etzcmm.getText().toString());
        map.put("email",etyx.getText().toString());
        app = (App) getApplication();
        db1 = app.getDb1();

        Button btnzc= (Button) findViewById(R.id.btnzc);
        btnzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    db1.save(etzh.getText().toString());
                    db1.save(etmm.getText().toString());
                    db1.save(etzcmm.getText().toString());
                    db1.save(etyx.getText().toString());
                } catch (DbException e) {
                    e.printStackTrace();
                }
//                Httputils httputils = app.getHttputils();
//                httputils.post("http://169.254.41.208/mobile/index.php?", map, Shujuku.class, new NetListnter() {
//                    @Override
//                    public void onSuccess(Basebean basebean) {
//                        Shujuku shujuku = (Shujuku) basebean;
//                        try {
//                            db1.save(shujuku);
//                        } catch (DbException e) {
//                            e.printStackTrace();
//                        }
                        Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
//                    }
//                });


            }
        });
    }
}
