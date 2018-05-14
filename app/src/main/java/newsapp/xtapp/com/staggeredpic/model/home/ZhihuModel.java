package newsapp.xtapp.com.staggeredpic.model.home;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.contract.main.ZhihuContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.zhihu.ZhihuDailyListBean;
import newsapp.xtapp.com.staggeredpic.model.http.ZhihuApi;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by Horrarndoo on 2017/9/12.
 * <p>
 */

public class ZhihuModel extends BaseModel implements ZhihuContract.IZhihuModel {

    @NonNull
    public static ZhihuModel newInstance() {
        return new ZhihuModel();
    }

    @Override
    public Observable<ZhihuDailyListBean> getDailyList(String date) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getDailyListWithDate
                (date).compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<ZhihuDailyListBean> getDailyList() {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getLastDailyList()
                .compose(RxHelper.<ZhihuDailyListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppApplicationUtil.getContext()).insertRead(DBConfig
                        .TABLE_ZHIHU, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
