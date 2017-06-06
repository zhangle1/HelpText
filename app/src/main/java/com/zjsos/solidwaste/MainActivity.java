package com.zjsos.solidwaste;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    private WebView mWebView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.green500));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mWebView = (WebView) findViewById(R.id.wb);
        mSwipeRefreshLayout.setRefreshing(true);

        final WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       mSwipeRefreshLayout.setRefreshing(false);
                    }
                },500);
                super.onPageFinished(view, url);
            }
        });
//        mWebView.loadUrl("https://192.168.8.91:8080/jxhbwf/mobile/gufei/jsp/add.jsp");
        mWebView.loadUrl("www.google.com");
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
