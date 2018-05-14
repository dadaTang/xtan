package newsapp.xtapp.com.staggeredpic.presenter.detail;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.contract.detail.GankIoDetailContract;
import newsapp.xtapp.com.staggeredpic.model.detail.GankIoDetailModel;

/**
 * Created by Horrarndoo on 2017/10/11.
 * <p>
 */

public class GankIoDetailPresenter extends GankIoDetailContract.GankIoDetailPresenter{
    @NonNull
    public static GankIoDetailPresenter newInstance() {
        return new GankIoDetailPresenter();
    }

    @Override
    public void loadGankIoDetail(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showGankIoDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public GankIoDetailContract.IGankIoDetailModel getModel() {
        return GankIoDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
