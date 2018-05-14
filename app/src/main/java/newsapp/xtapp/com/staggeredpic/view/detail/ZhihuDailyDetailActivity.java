package newsapp.xtapp.com.staggeredpic.view.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.contract.detail.ZhihuDetailContract;
import newsapp.xtapp.com.staggeredpic.model.bean.zhihu.ZhihuDailyDetailBean;
import newsapp.xtapp.com.staggeredpic.presenter.detail.ZhihuDetailPresenter;
import newsapp.xtapp.com.staggeredpic.util.HtmlUtils;
import newsapp.xtapp.com.staggeredpic.util.ResourcesUtils;

/**
 * Created by Horrarndoo on 2017/9/13.
 * <p>
 */

public class ZhihuDailyDetailActivity extends BaseWebViewLoadActivity<ZhihuDetailContract
        .ZhihuDetailPresenter> implements ZhihuDetailContract.IZhihuDetailView {

    private String mId, mTitle;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_ID);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_ZHIHU_DETAIL_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvDetailTitle.setText(mTitle);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ZhihuDetailPresenter.newInstance();
    }

    @Override
    public void showDailyDetail(ZhihuDailyDetailBean bean) {
        flNetView.setVisibility(View.GONE);
        Glide.with(mContext).load(bean.getImage()).transition(new DrawableTransitionOptions().crossFade()).into(ivDetail);
        tvDetailTitle.setText(bean.getTitle());
        tvDetailcopyright.setText(bean.getImage_source());
        String htmlData = HtmlUtils.createHtmlData(bean.getBody(), bean.getCss(), bean.getJs());
        nswvDetailContent.loadData(htmlData, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadDailyDetail(mId);
    }

    @Override
    protected String getToolbarTitle() {
        return ResourcesUtils.getString(R.string.zhihu_detail_title);
    }
}
