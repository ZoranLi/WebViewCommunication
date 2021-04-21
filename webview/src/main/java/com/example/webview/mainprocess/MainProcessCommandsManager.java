package com.example.webview.mainprocess;

import android.os.RemoteException;

import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;
import com.example.webview.IWebviewProcessToMainProcessInterface;
import com.example.webview.command.Command;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager extends IWebviewProcessToMainProcessInterface.Stub {

    private static MainProcessCommandsManager sInstance;

    private HashMap<String,Command> commandHashMap = new HashMap<>();

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

    private MainProcessCommandsManager(){
        ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        for (Command command : commandServiceLoader) {
            if(!commandHashMap.containsKey(command.name())){
                commandHashMap.put(command.name(),command);
            }
        }
    }

    public void executeCommand(String commandName, Map params,ICallbackMainprocessToWebViewPorcessInterface callback) {
        commandHashMap.get(commandName).execute(params,callback);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackMainprocessToWebViewPorcessInterface callback) throws RemoteException {
        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
