// IWebviewProcessToMainProcessInterface.aidl
package com.example.webview;

// Declare any non-default types here with import statements

import com.example.webview.ICallbackMainprocessToWebViewPorcessInterface;

interface IWebviewProcessToMainProcessInterface {
//    /**
//     * Demonstrates some basic types that you can use as parameters
//     * and return values in AIDL.
//     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    void handleWebCommand(String commandName,String jsonParams,in ICallbackMainprocessToWebViewPorcessInterface callback);

}