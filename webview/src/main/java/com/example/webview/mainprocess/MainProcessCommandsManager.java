package com.example.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;

import com.example.base.BaseApplication;
import com.example.webview.IWebviewProcessToMainProcessInterface;
import com.google.gson.Gson;

import java.util.Map;

public class MainProcessCommandsManager extends IWebviewProcessToMainProcessInterface.Stub {

    private static MainProcessCommandsManager sInstance;

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                if (sInstance == null) {
                    sInstance = new MainProcessCommandsManager();
                }
            }
        }
        return sInstance;
    }

    public void executeCommand(String commandName, Map params) {
        if ("openPage".equalsIgnoreCase(commandName)) {
            String targetClass = String.valueOf(params.get("target_class"));
            if (!TextUtils.isEmpty(targetClass)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.application,targetClass));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.application.startActivity(intent);
            }
        }
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) throws RemoteException {
        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class));
    }
}
