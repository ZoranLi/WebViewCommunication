package com.example.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.webview.databinding.LayoutFragmentWebviewBinding;
import com.example.webview.utils.Constans;
import com.example.webview.webchromeclient.XiangxueWebviewChromeClient;
import com.example.webview.webviewclient.XiangxueWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 加入loading
 */
public class WebViewFragment extends Fragment implements WebViewCallBack, OnRefreshListener {

    private String url;
    LayoutFragmentWebviewBinding layoutFragmentWebviewBinding;

    private LoadService loadService;
    private boolean canNativeRefresh;
    private boolean isError;
    private static final String TAG = WebViewFragment.class.getSimpleName();

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constans.URL, url);
        bundle.putBoolean(Constans.CAN_NATIVE_REFRESH, canNativeRefresh);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(Constans.URL);
            canNativeRefresh = bundle.getBoolean(Constans.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutFragmentWebviewBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_webview, container, false);
        WebSettings settings = layoutFragmentWebviewBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        layoutFragmentWebviewBinding.webView.loadUrl(url);
        loadService = LoadSir.getDefault().register(layoutFragmentWebviewBinding.smartRefreshLayout, (Callback.OnReloadListener) v -> {
            loadService.showCallback(LoadingCallback.class);
            layoutFragmentWebviewBinding.webView.reload();
        });
        layoutFragmentWebviewBinding.webView.setWebViewClient(new XiangxueWebViewClient(this));
        layoutFragmentWebviewBinding.webView.setWebChromeClient(new XiangxueWebviewChromeClient(this));
        layoutFragmentWebviewBinding.smartRefreshLayout.setEnableRefresh(canNativeRefresh);
        layoutFragmentWebviewBinding.smartRefreshLayout.setOnRefreshListener(this);
        layoutFragmentWebviewBinding.smartRefreshLayout.setEnableLoadMore(false);
        return loadService.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (isError) {
            layoutFragmentWebviewBinding.smartRefreshLayout.setEnableRefresh(true);
        } else {
            layoutFragmentWebviewBinding.smartRefreshLayout.setEnableRefresh(canNativeRefresh);
        }
        layoutFragmentWebviewBinding.smartRefreshLayout.finishRefresh();
        Log.d(TAG, "pageFinished");
        if (loadService != null) {
            if (isError) {
                loadService.showCallback(ErrorCallback.class);
            } else {
                loadService.showSuccess();
            }
        }
        isError = false;
    }

    @Override
    public void onError() {
        Log.e(TAG, "ONERROR");
        isError = true;
        layoutFragmentWebviewBinding.smartRefreshLayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        layoutFragmentWebviewBinding.webView.reload();
    }
}
