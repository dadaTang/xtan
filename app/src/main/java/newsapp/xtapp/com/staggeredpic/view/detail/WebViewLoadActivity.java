package newsapp.xtapp.com.staggeredpic.view.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.contract.detail.WebViewLoadConaract;
import newsapp.xtapp.com.staggeredpic.presenter.detail.WebViewLoadPresenter;
import newsapp.xtapp.com.staggeredpic.util.ScreenUtil;
import newsapp.xtapp.com.staggeredpic.util.StatusBarUtils;

/**
 * Created by Horrarndoo on 2017/10/20.
 * <p>
 * Webview加载Url详情页
 */

public class WebViewLoadActivity extends BaseWebViewLoadActivity<WebViewLoadConaract
        .WebViewLoadPresenter> implements WebViewLoadConaract.IWebViewLoadView {

    private String mTitle, mUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0)
                .getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度
        params.height = ScreenUtil.dp2px(56) + StatusBarUtils.getStatusBarHeight
                (mContext);
    }

    @Override
    public void showUrlDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadUrl(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WebViewLoadPresenter.newInstance();
    }


}
