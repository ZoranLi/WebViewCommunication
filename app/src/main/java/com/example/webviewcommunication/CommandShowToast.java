package com.example.webviewcommunication;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.base.BaseApplication;
import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, ICallbackMainprocessToWebViewPorcessInterface callback) {
        Object o = parameters.get("message");
//        Looper.myQueue().addIdleHandler(() -> {
//            Toast.makeText(BaseApplication.application, String.valueOf(o), Toast.LENGTH_SHORT).show();
//            return false;
//        });
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            Toast.makeText(BaseApplication.application, String.valueOf(o), Toast.LENGTH_SHORT).show();
        });
    }
}
