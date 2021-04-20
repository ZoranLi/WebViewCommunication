package autoservice;

import android.content.Context;

/**
 * 接口下沉
 */
public interface IWebviewService {
    void startActivity(Context context,String url,String title);
}
