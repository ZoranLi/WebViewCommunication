package com.example.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.webview.utils.Constans;
import com.google.auto.service.AutoService;

import java.net.URL;

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
    public Fragment getWevViewFragment(String url) {
        return WebViewFragment.newInstance(url);
    }
}
