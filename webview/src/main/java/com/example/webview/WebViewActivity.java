package com.example.webview;

import android.os.Bundle;
import android.webkit.WebSettings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.webview.databinding.LayoutActivityWebviewBinding;

public class WebViewActivity extends AppCompatActivity {
    LayoutActivityWebviewBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(WebViewActivity.this, R.layout.layout_activity_webview);
//        dataBinding.webView.setJavaScriptEnabled(true);
        WebSettings settings = dataBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        dataBinding.webView.loadUrl("https://www.baidu.com");
    }
}
