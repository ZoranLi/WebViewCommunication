package com.example.webview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.webview.databinding.LayoutActivityWebviewBinding;
import com.example.webview.utils.Constans;

public class WebViewActivity extends AppCompatActivity {
    LayoutActivityWebviewBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(WebViewActivity.this, R.layout.layout_activity_webview);
        dataBinding.title.setText(getIntent().getStringExtra(Constans.TITLE));

        dataBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constans.SHOWACITONBAR, false) ? View.VISIBLE : View.GONE);
        dataBinding.ivClose.setOnClickListener(v -> WebViewActivity.this.finish());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WebViewFragment webViewFragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constans.URL));
        fragmentTransaction.replace(R.id.web_view_fragment, webViewFragment).commit();
    }
}
