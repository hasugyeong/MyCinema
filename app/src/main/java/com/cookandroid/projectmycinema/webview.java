package com.cookandroid.projectmycinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webview  extends AppCompatActivity {

    Button bcgv,blotte,bmega;
    WebView web;
    Button b1;
    static String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        web=(WebView)findViewById(R.id.webView1);
        Intent intent = getIntent();
        Button bcgv=(Button)findViewById(R.id.gocgv);
        Button blotte=(Button)findViewById(R.id.golottecinema);
        Button bmega=(Button)findViewById(R.id.gomegabox);
        Button b1=(Button)findViewById(R.id.btnback2);

        String url= intent.getStringExtra("url");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClientClass());
        web.loadUrl(url); // 접속 URL


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }//oncreate
    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }



}
