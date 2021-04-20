package com.example.webview.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webview.WebViewCallBack;

public class XiangxueWebViewClient extends WebViewClient {
    WebViewCallBack webViewCallBack;
    private static final String TAG = XiangxueWebViewClient.class.getSimpleName();

    public XiangxueWebViewClient(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (webViewCallBack != null) {
            webViewCallBack.pageStarted(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (webViewCallBack != null) {
            webViewCallBack.pageFinished(url);
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (webViewCallBack != null) {
            webViewCallBack.onError();
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
    }
}
