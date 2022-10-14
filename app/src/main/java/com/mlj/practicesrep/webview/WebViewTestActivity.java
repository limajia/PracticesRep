package com.mlj.practicesrep.webview;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.mlj.practicesrep.R;

public class WebViewTestActivity extends AppCompatActivity {

    private WebView myWebview;
    private static final String URL = "https://www.wanandroid.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        initView();
        /*1. shouldOverrideUrlLoading 不设置的话。点击页面中的连接，会跳转到手机默认的web应用打开。如果有多个的话，会给提示进行选择*/
        //myWebview.loadUrl(URL);

        /*2.*/
        myWebview.setWebViewClient(new WebViewClient() {
            //Give the host application a chance to take control when a URL is about to be loaded in the current WebView.
            //他这个意思，就是理解为是否给host application（手机默认的web应用）一个处理的机会，true，就是给，false就是不给。
            //如果给了处理机会，点击不会跳转，相当于程序代理打开程序了，可以自己指定打开的web应用。
            //这个函数就是处理页面中的跳转和点击的，第一次load，不会走
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                System.out.println("docker567: url="+ request.getUrl());
                return super.shouldOverrideUrlLoading(view,request); //默认是返回false
            }
        });
        myWebview.loadUrl(URL);
        myWebview.setWebChromeClient(new WebChromeClient());
    }

    private void initView() {
        myWebview = findViewById(R.id.my_webview);
    }
}