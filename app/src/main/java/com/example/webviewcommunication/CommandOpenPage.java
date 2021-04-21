package com.example.webviewcommunication;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.example.base.BaseApplication;
import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;
@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, ICallbackMainprocessToWebViewPorcessInterface callback) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.application, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.application.startActivity(intent);
        }
    }
}
