package com.example.zhang.abercrombiedemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhang.abercrombiedemo.R;

/**
 * Activity for show WebView
 *
 * This activity is used to display WebView from previews RecyclerView
 *
 * @author Andrew Zhang
 * @version 1.0
 * @since 2016-10-31
 */
public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        String url =  intent.getStringExtra("webView");
        webView = (WebView) findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
