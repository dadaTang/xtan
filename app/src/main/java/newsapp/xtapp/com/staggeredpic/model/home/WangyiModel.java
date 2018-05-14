package newsapp.xtapp.com.staggeredpic.model.home;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.contract.main.WangyiContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.wangyi.WangyiNewsListBean;
import newsapp.xtapp.com.staggeredpic.model.http.WangyiApi;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 */

public class WangyiModel implements WangyiContract.IWangyiModel {
    @NonNull
    public static WangyiModel newInstance() {
        return new WangyiModel();
    }

    @Override
    public Observable<WangyiNewsListBean> getNewsList(int id) {
        return RetrofitCreateHelper.createApi(WangyiApi.class, WangyiApi.HOST).getNewsList(id)
                .compose(RxHelper.<WangyiNewsListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppApplicationUtil.getContext()).insertRead(DBConfig
                        .TABLE_WANGYI, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
