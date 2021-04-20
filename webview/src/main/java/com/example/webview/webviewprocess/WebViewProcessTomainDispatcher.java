package com.example.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.base.BaseApplication;
import com.example.webview.IWebviewProcessToMainProcessInterface;
import com.example.webview.mainprocess.MainProcessService;

/**
 * 建立连接
 */
public class WebViewProcessTomainDispatcher implements ServiceConnection {
    private static WebViewProcessTomainDispatcher sInstance;
    private IWebviewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    public static WebViewProcessTomainDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessTomainDispatcher.class) {
                if (sInstance == null) {
                    sInstance = new WebViewProcessTomainDispatcher();
                }
            }
        }
        return sInstance;
    }


    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.application, MainProcessService.class);
        BaseApplication.application.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewProcessToMainProcessInterface = IWebviewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    public void executeCommand(String commandName, String parasm) {
        if (iWebviewProcessToMainProcessInterface != null) {
            try {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName,parasm);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
