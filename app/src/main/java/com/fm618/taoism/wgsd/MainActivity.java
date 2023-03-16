package com.fm618.taoism.wgsd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = this.getSharedPreferences("WGSD", MODE_PRIVATE);


        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("BOOK", url);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("lastUrl", url);
                editor.apply();
            }
        });

        String url = sp.getString("lastUrl", "");
        if (url.isEmpty()) {
            myWebView.loadUrl("https://m.xbiqugela.com/book_11137");
        } else {
            myWebView.loadUrl(url);
        }

    }
}