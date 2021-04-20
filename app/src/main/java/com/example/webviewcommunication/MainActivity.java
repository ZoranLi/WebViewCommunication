package com.example.webviewcommunication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.base.autoservice.XiangxueServiceLoader;

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
//                    iWebviewService.startActivity(MainActivity.this,"https://www.baidu.com","百度",true);
                    iWebviewService.startDemoHtml(MainActivity.this);
                }
            }
        });
    }
}