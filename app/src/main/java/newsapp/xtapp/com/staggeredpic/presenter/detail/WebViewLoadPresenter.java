package newsapp.xtapp.com.staggeredpic.presenter.detail;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;


import newsapp.xtapp.com.staggeredpic.contract.detail.WebViewLoadConaract;
import newsapp.xtapp.com.staggeredpic.model.detail.WebViewLoadModel;

/**
 * Created by Horrarndoo on 2017/10/20.
 * <p>
 */

public class WebViewLoadPresenter extends WebViewLoadConaract.WebViewLoadPresenter {

    @NonNull
    public static WebViewLoadPresenter newInstance() {
        return new WebViewLoadPresenter();
    }

    @Override
    public void loadUrl(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showUrlDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public WebViewLoadConaract.IWebViewLoadModel getModel() {
        return WebViewLoadModel.newInstance();
    }

    @Override
    public void onStart() {
    }

}
