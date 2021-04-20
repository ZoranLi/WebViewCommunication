package autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * 接口下沉
 */
public interface IWebviewService {
    void startActivity(Context context, String url, String title, boolean isShowActionBar);

    Fragment getWevViewFragment(String url,boolean canNativeRefresh);

    void startDemoHtml(Context context);
}
