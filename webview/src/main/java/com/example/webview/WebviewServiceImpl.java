package com.example.webview;

import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;

import autoservice.IWebviewService;

@AutoService(IWebviewService.class)
public class WebviewServiceImpl implements IWebviewService {
    @Override
    public void startActivity(Context context, String url, String title) {
        if (context != null) {
            context.startActivity(new Intent(context,WebViewActivity.class));
        }
    }
}
