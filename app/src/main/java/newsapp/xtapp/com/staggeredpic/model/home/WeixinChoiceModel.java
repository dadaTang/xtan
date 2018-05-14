package newsapp.xtapp.com.staggeredpic.model.home;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.contract.main.WeixinContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.weixin.WeixinChoiceListBean;
import newsapp.xtapp.com.staggeredpic.model.http.WeixinApi;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by Horrarndoo on 2017/9/21.
 * <p>
 */

public class WeixinChoiceModel extends BaseModel implements WeixinContract.IWeixinModel {

    @NonNull
    public static WeixinChoiceModel newInstance() {
        return new WeixinChoiceModel();
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppApplicationUtil.getContext()).insertRead(DBConfig
                        .TABLE_WEIXIN, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }

    @Override
    public Observable<WeixinChoiceListBean> getWeixinChoiceList(int page, int pageStrip, String
            dttype, String key) {
        return RetrofitCreateHelper.createApi(WeixinApi.class, WeixinApi.HOST).getWeixinChoiceList
                (page, pageStrip, dttype, key).compose(RxHelper
                .<WeixinChoiceListBean>rxSchedulerHelper());
    }
}
