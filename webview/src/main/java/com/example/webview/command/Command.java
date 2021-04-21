package com.example.webview.command;

import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map parameters, ICallbackMainprocessToWebViewPorcessInterface callbakc);

}
