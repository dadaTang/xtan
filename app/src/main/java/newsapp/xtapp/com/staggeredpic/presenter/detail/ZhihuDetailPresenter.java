package newsapp.xtapp.com.staggeredpic.presenter.detail;

import android.support.annotation.NonNull;


import io.reactivex.functions.Consumer;
import newsapp.xtapp.com.staggeredpic.contract.detail.ZhihuDetailContract;
import newsapp.xtapp.com.staggeredpic.model.bean.zhihu.ZhihuDailyDetailBean;
import newsapp.xtapp.com.staggeredpic.model.detail.ZhihuDetailModel;

/**
 * Created by Horrarndoo on 2017/9/13.
 * <p>
 */

public class ZhihuDetailPresenter extends ZhihuDetailContract.ZhihuDetailPresenter {

    @NonNull
    public static ZhihuDetailPresenter newInstance() {
        return new ZhihuDetailPresenter();
    }

    @Override
    public void loadDailyDetail(String id) {
        if (mIModel == null)
            return;
        mRxManager.register(mIModel.getDailyDetail(id).subscribe(new Consumer<ZhihuDailyDetailBean>() {
            @Override
            public void accept(ZhihuDailyDetailBean zhihuDailyDetailBean) throws Exception {
                if (mIView != null)
                    mIView.showDailyDetail(zhihuDailyDetailBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    mIView.showToast("网络异常");
                    mIView.showNetworkError();
                }
            }
        }));
    }

    @Override
    public ZhihuDetailContract.IZhihuDetailModel getModel() {
        return ZhihuDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
