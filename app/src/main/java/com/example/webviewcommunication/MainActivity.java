package com.example.webviewcommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.base.autoservice.XiangxueServiceLoader;
import com.example.webview.WebViewActivity;

import java.util.Iterator;
import java.util.ServiceLoader;

import autoservice.IWebviewService;

/**
 * 组件化
 * arouter https://github.com/alibaba/ARouter
 * cc  https://github.com/luckybilly/CC
 * autoservice google
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.openWebView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IWebviewService iWebviewService = ServiceLoader.load(IWebviewService.class).iterator().next();
                IWebviewService iWebviewService = XiangxueServiceLoader.load(IWebviewService.class);
                if (iWebviewService != null) {
                    iWebviewService.startActivity(MainActivity.this,"https://www.baidu.com","百度");
                }
            }
        });
    }
}