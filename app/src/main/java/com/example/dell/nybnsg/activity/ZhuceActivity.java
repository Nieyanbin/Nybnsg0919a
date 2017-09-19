package com.example.dell.nybnsg.activity;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.nybnsg.R;
import com.example.dell.nybnsg.bean.Basebean;
import com.example.dell.nybnsg.bean.Registerbean;
import com.example.dell.nybnsg.bean.Shujuku;
import com.example.dell.nybnsg.bean.Zhecebean;
import com.example.dell.nybnsg.http.App;
import com.example.dell.nybnsg.http.Httputils;
import com.example.dell.nybnsg.http.NetListnter;
import com.google.gson.Gson;
import com.koma.greendao.gen.GreendaobeanDao;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
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
    private App app;
    private Httputils httputils;

    //private Map<String,String> map=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        x.view().inject(this);
        //找控件
        etzh = (EditText) findViewById(R.id.etzh);
        etmm = (EditText) findViewById(R.id.etmm);
        etzcmm = (EditText) findViewById(R.id.etzcmm);
        etyx = (EditText) findViewById(R.id.etyx);
        App app = (App) getApplication();
        httputils = app.getHttputils();
        Button btnzc= (Button) findViewById(R.id.btnzc);
        btnzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("数据","数据："+etzh.getText().toString());
                Log.e("数据","数据："+etmm.getText().toString());
                Log.e("数据","数据："+etzcmm.getText().toString());
                Log.e("数据","数据："+etyx.getText().toString());
                String name = etzh.getText().toString().trim();
                String password = etmm.getText().toString().trim();
                String rePassword = etzcmm.getText().toString().trim();
                String yx = etyx.getText().toString().trim();
//                if (!name.equals("")&&!password.equals("")&&!rePassword.equals("")){
//                    if (password.equals(rePassword)){
//                        Registerbean userBean = new Registerbean();
//                        userBean.setZhanghao(name);
//                        userBean.setPwd(password);
//                    }else {
//                        Toast.makeText(ZhuceActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
//                        etmm.setText("");
//                        etzcmm.setText("");
//                    }
//                }else {
//                    Toast.makeText(ZhuceActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
//                    etmm.setText("");
//                    etzcmm.setText("");
//                    etzh.setText("");
//                    etyx.setText("");
//                }
                RequestParams params=new RequestParams("http://169.254.41.208/mobile/index.php?act=login&op=register");
                params.addBodyParameter("username",etzh.getText().toString());
                params.addBodyParameter("password",etmm.getText().toString());
                params.addBodyParameter("password_confirm",etzcmm.getText().toString());
                params.addBodyParameter("email",etyx.getText().toString());
                params.addBodyParameter("client","android");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("result","result:"+result);
                        Gson gson=new Gson();

                        Zhecebean zhecebean = gson.fromJson(result, Zhecebean.class);

                        int code = zhecebean.getCode();
                        Log.e("dasdasdadasda","code值"+code);

                        if(code==200){
                            Toast.makeText(ZhuceActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            String key = zhecebean.getDatas().getKey();
                            Log.e("KEY","KEY:"+key.toString());
                        }else{
                            Toast.makeText(ZhuceActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
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

//                    }
//                });



        });
    }
}
