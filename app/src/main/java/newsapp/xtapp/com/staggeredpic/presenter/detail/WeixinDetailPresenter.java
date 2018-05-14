package newsapp.xtapp.com.staggeredpic.presenter.detail;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.contract.detail.WeixinDetailContract;
import newsapp.xtapp.com.staggeredpic.model.detail.WeixinDetailModel;


/**
 * Created by Horrarndoo on 2017/9/21.
 * <p>
 */

public class WeixinDetailPresenter extends WeixinDetailContract.WeixinDetailPresenter{
    @NonNull
    public static WeixinDetailPresenter newInstance() {
        return new WeixinDetailPresenter();
    }

    @Override
    public void loadWeixinChoiceDetail(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showWeixinChoiceDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public WeixinDetailContract.IWeixinDetailModel getModel() {
        return WeixinDetailModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
