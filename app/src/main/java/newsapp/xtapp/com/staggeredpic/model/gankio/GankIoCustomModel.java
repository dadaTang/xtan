package newsapp.xtapp.com.staggeredpic.model.gankio;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.contract.gankio.GankIoCustomContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoCustomItemBean;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoCustomListBean;
import newsapp.xtapp.com.staggeredpic.model.http.GankioApi;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by Horrarndoo on 2017/10/13.
 * <p>
 */

public class GankIoCustomModel extends BaseModel implements GankIoCustomContract
        .IGankIoCustomModel {

    @NonNull
    public static GankIoCustomModel newInstance() {
        return new GankIoCustomModel();
    }

    @Override
    public Observable<GankIoCustomListBean> getCustomGankIoList(String type, int prePage, int
            page) {
        return RetrofitCreateHelper.createApi(GankioApi.class, GankioApi.HOST)
                .getGankIoCustomList(type, prePage, page)
                .map(new Function<GankIoCustomListBean, GankIoCustomListBean>() {
                    @Override
                    public GankIoCustomListBean apply(GankIoCustomListBean gankIoCustomListBean)
                            throws Exception {
                        for (GankIoCustomItemBean bean : gankIoCustomListBean.getResults()) {
                            if (bean.getType().equals("福利")) {
                                bean.itemType = GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE;
                            } else if (bean.getImages() != null) {
                                if (bean.getImages().size() > 0 && !TextUtils.isEmpty(bean
                                        .getImages().get(0)))
                                    bean.itemType = GankIoCustomItemBean
                                            .GANK_IO_DAY_ITEM_CUSTOM_NORMAL;
                            } else {
                                bean.itemType = GankIoCustomItemBean
                                        .GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE;
                            }
                        }
                        return gankIoCustomListBean;
                    }
                })
                .compose(RxHelper.<GankIoCustomListBean>rxSchedulerHelper());
    }

    @Override
    public Observable<Boolean> recordItemIsRead(final String key) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(DBUtils.getDB(AppApplicationUtil.getContext()).insertRead(DBConfig
                        .TABLE_GANKIO_CUSTOM, key, ItemState.STATE_IS_READ));
                emitter.onComplete();
            }
        }).compose(RxHelper.<Boolean>rxSchedulerHelper());
    }
}
