package newsapp.xtapp.com.staggeredpic.view.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.contract.detail.GankIoDetailContract;
import newsapp.xtapp.com.staggeredpic.presenter.detail.GankIoDetailPresenter;
import newsapp.xtapp.com.staggeredpic.util.ScreenUtil;
import newsapp.xtapp.com.staggeredpic.util.StatusBarUtils;
import newsapp.xtapp.com.staggeredpic.util.imageutils.DisplayUtils;


/**
 * Created by Horrarndoo on 2017/10/11.
 * <p>
 */

public class GankIoDetailActivity extends BaseWebViewLoadActivity<GankIoDetailContract
        .GankIoDetailPresenter> implements GankIoDetailContract.IGankIoDetailView {

    private String mTitle, mUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_GANKIO_DETAIL_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_GANKIO_DETAIL_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0)
                .getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度，重新定义AppBarLayout的高度
        params.height = ScreenUtil.dp2px(56) + StatusBarUtils.getStatusBarHeight
                (mContext);
    }

    @Override
    public void showGankIoDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadGankIoDetail(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return GankIoDetailPresenter.newInstance();
    }
}
