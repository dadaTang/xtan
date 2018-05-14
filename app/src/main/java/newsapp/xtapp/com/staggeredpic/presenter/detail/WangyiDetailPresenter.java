package newsapp.xtapp.com.staggeredpic.presenter.detail;

import android.support.annotation.NonNull;


import io.reactivex.functions.Consumer;
import newsapp.xtapp.com.staggeredpic.contract.detail.WangyiDetailContract;
import newsapp.xtapp.com.staggeredpic.helper.JsonHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.wangyi.WangyiNewsDetailBean;
import newsapp.xtapp.com.staggeredpic.model.detail.WangyiDetailModel;
import okhttp3.ResponseBody;

/**
 * Created by Horrarndoo on 2017/9/19.
 * <p>
 */

public class WangyiDetailPresenter extends WangyiDetailContract.WangyiDetailPresenter {
    @NonNull
    public static WangyiDetailPresenter newInstance() {
        return new WangyiDetailPresenter();
    }

    @Override
    public void loadNewsDetailWithUrl(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showNewsDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public void loadNewsDetailWithId(final String id) {
        mRxManager.register(mIModel.getNewsDetail(id).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                if (mIView == null)
                    return;
                //新闻的Json数据比较特殊，返回的json key不固定，需要手动的获取json数据，然后再用gson解析
                WangyiNewsDetailBean bean = JsonHelper.getNewsDetailBeans(responseBody.string(),
                        id);
                mIView.showNewsDetail(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public WangyiDetailContract.IWangyiDetailModel getModel() {
        return WangyiDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
