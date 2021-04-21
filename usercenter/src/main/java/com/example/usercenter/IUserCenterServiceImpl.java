package com.example.usercenter;

import android.content.Intent;

import com.example.base.BaseApplication;
import com.google.auto.service.AutoService;

import autoservice.IUserCenterService;

@AutoService({IUserCenterService.class})
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.application,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.application.startActivity(intent);
    }
}
