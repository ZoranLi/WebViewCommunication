package com.example.webviewcommunication;

import android.os.RemoteException;
import android.util.Log;

import com.example.base.autoservice.XiangxueServiceLoader;
import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import autoservice.IUserCenterService;
import eventbus.LoginEvent;

@AutoService({Command.class})
public class CommandLogin implements Command {

    IUserCenterService iUserCenterService = XiangxueServiceLoader.load(IUserCenterService.class);
    private ICallbackMainprocessToWebViewPorcessInterface callBack;

    private String callbacknameFromeNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackMainprocessToWebViewPorcessInterface callback) {
//        String targetClass = String.valueOf(parameters.get("target_class"));
//        if (!TextUtils.isEmpty(targetClass)) {
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName(BaseApplication.application, targetClass));
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            BaseApplication.application.startActivity(intent);
//        }
        Log.d(getClass().getName(), new Gson().toJson(parameters));

        if (iUserCenterService != null && !iUserCenterService.isLogined()) {
            this.callBack = callback;
            this.callbacknameFromeNativeJs = (String) parameters.get("callbackname");
            iUserCenterService.login();

        }
    }

    @Subscribe
    public void onMessage(LoginEvent loginEvent) {
        Log.d("ssss", loginEvent.username);
        HashMap hashMap = new HashMap();
        hashMap.put("accountName", loginEvent.username);
        if (this.callBack != null) {
            try {
                this.callBack.onResult(callbacknameFromeNativeJs,new Gson().toJson(hashMap));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }
}
