package newsapp.xtapp.com.staggeredpic.presenter.gankio;

import android.os.Bundle;
import android.support.annotation.NonNull;

import io.reactivex.functions.Consumer;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.contract.gankio.GankIoWelfareContract;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareItemBean;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareListBean;
import newsapp.xtapp.com.staggeredpic.model.gankio.GankIoWelfareModel;
import newsapp.xtapp.com.staggeredpic.view.image.ImageBrowseActivity;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class GankIoWelfarePresenter extends GankIoWelfareContract.GankIoWelfarePresenter {
    private int mCurrentPage;
    private boolean isLoading;

    @NonNull
    public static GankIoWelfarePresenter newInstance() {
        return new GankIoWelfarePresenter();
    }

    @Override
    public void loadLatestList() {
        if (mIModel == null || mIView == null)
            return;

        mCurrentPage = 1;
        mRxManager.register(mIModel.getWelfareList(10, mCurrentPage).subscribe(new Consumer<GankIoWelfareListBean>() {
            @Override
            public void accept(GankIoWelfareListBean gankIoWelfareListBean) throws Exception {
                if (mIView == null)
                    return;

                if (gankIoWelfareListBean.isError()) {
                    mIView.showNetworkError();
                } else {
                    mCurrentPage++;
                    mIView.updateContentList(gankIoWelfareListBean.getResults());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    if (mIView.isVisiable())
                        mIView.showToast("Network error.");
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    public void loadMoreList() {
        if (!isLoading) {
            isLoading = true;
            //一次加载20条数据
            mRxManager.register(mIModel.getWelfareList(10, mCurrentPage).subscribe(new Consumer<GankIoWelfareListBean>() {
                @Override
                public void accept(GankIoWelfareListBean gankIoWelfareListBean) throws
                        Exception {
                    isLoading = false;
                    if (mIView == null)
                        return;

                    if (gankIoWelfareListBean.isError()) {
                        mIView.showNetworkError();
                    } else {
                        if (gankIoWelfareListBean.getResults().size() > 0) {
                            mCurrentPage++;
                            mIView.updateContentList(gankIoWelfareListBean.getResults());
                        } else {
                            mIView.showNoMoreData();
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    isLoading = false;
                    if (mIView != null) {
                        mIView.showLoadMoreError();
                    }
                }
            }));
        }
    }

    @Override
    public void onItemClick(int position, GankIoWelfareItemBean item) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.ARG_KEY_IMAGE_BROWSE_URL, item.getUrl());
        mIView.startNewActivity(ImageBrowseActivity.class, bundle);
    }


    @Override
    public GankIoWelfareContract.IGankIoWelfareModel getModel() {
        return GankIoWelfareModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
