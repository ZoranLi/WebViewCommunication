package com.example.webview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.webview.bean.JsParam;
import com.example.webview.webviewprocess.WebViewProcessTomainDispatcher;
import com.example.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.example.webview.webviewprocess.webchromeclient.XiangxueWebviewChromeClient;
import com.example.webview.webviewprocess.webviewclient.XiangxueWebViewClient;
import com.google.gson.Gson;

public class BaseWebView extends WebView {
    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewProcessTomainDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "xiangxuewebview");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        this.setWebViewClient(new XiangxueWebViewClient(webViewCallBack));
        this.setWebChromeClient(new XiangxueWebviewChromeClient(webViewCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam objec = new Gson().fromJson(jsParam, JsParam.class);
            if (objec != null) {
//                if ("showToast".equalsIgnoreCase(objec.name)) {
//                    Object o = new Gson().fromJson(objec.param, Map.class).get("message");
//                    Toast.makeText(getContext(), String.valueOf(o), Toast.LENGTH_SHORT).show();
//                }else{
//                    WebViewProcessTomainDispatcher.getInstance().executeCommand(objec.name,new Gson().toJson(objec.param));
//                }
                WebViewProcessTomainDispatcher.getInstance().executeCommand(objec.name,new Gson().toJson(objec.param),this);
            }
        }
    }


    public void handleCallback(String callback,String response){
        if(!TextUtils.isEmpty(callback)){

            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:xiaoxuejs.callback('"+callback+"','"+response+"')";
                    evaluateJavascript(jscode,null);
                }
            });

        }
    }


}
