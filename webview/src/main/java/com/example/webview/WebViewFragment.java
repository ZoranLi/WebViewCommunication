package com.example.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.webview.databinding.LayoutFragmentWebviewBinding;
import com.example.webview.utils.Constans;

/**
 * 加入loading
 */
public class WebViewFragment extends Fragment {

    private String url;
    LayoutFragmentWebviewBinding layoutFragmentWebviewBinding;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constans.URL, url);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(Constans.URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutFragmentWebviewBinding = DataBindingUtil.inflate(inflater, R.layout.layout_fragment_webview, container, false);
        WebSettings settings = layoutFragmentWebviewBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        layoutFragmentWebviewBinding.webView.loadUrl(url);
        return layoutFragmentWebviewBinding.getRoot();
    }
}
