package com.example.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.webview.utils.Constans;
import com.google.auto.service.AutoService;

import autoservice.IWebviewService;

@AutoService(IWebviewService.class)
public class WebviewServiceImpl implements IWebviewService {
    @Override
    public void startActivity(Context context, String url, String title,boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constans.URL,url);
            intent.putExtra(Constans.TITLE,title);
            intent.putExtra(Constans.SHOWACITONBAR,isShowActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWevViewFragment(String url,boolean canNativeRefresh) {
        return WebViewFragment.newInstance(url,canNativeRefresh);
    }

    @Override
    public void startDemoHtml(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constans.URL,Constans.ANDROID_ASSET_URI);
        intent.putExtra(Constans.TITLE,"本地");
        intent.putExtra(Constans.SHOWACITONBAR,true);
        context.startActivity(intent);
    }
}
