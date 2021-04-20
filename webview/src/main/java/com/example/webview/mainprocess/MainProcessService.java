package com.example.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MainProcessService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //做连接用

        return MainProcessCommandsManager.getInstance();
    }
}
