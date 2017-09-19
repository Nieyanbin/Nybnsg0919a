package com.example.dell.nybnsg.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.dell.nybnsg.R;

public class WebviewActivity extends AppCompatActivity {

    private String goods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
        WebView mWebView= (WebView) findViewById(R.id.web);
        WebSettings wSet = mWebView.getSettings();
        wSet.setUseWideViewPort(true);
        wSet.setLoadWithOverviewMode(true);
        String str = "http://169.254.41.208/mobile/index.php?act=goods&op=goods_body&goods_id="+goods_id;
        mWebView.loadUrl(str);
    }
}
