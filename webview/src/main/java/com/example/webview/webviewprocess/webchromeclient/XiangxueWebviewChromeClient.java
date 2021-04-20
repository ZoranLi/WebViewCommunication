package com.example.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.webview.WebViewCallBack;

public class XiangxueWebviewChromeClient extends WebChromeClient {

    private WebViewCallBack webViewCallBack;
    private static final String TAG = "XiangxueWebview";

    public XiangxueWebviewChromeClient(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (webViewCallBack != null) {
            webViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "WebViewCallBack is null");
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG,consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
