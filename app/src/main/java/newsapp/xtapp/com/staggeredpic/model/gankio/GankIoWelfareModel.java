package newsapp.xtapp.com.staggeredpic.model.gankio;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.gankio.GankIoWelfareContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareListBean;
import newsapp.xtapp.com.staggeredpic.model.http.GankioApi;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class GankIoWelfareModel extends BaseModel implements GankIoWelfareContract
        .IGankIoWelfareModel {

    @NonNull
    public static GankIoWelfareModel newInstance() {
        return new GankIoWelfareModel();
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        //不记录
        return null;
    }

    @Override
    public Observable<GankIoWelfareListBean> getWelfareList(int pre_page, int page) {
        return RetrofitCreateHelper.createApi(GankioApi.class, GankioApi.HOST)
                .getGankIoWelfareList(pre_page, page).compose(RxHelper
                        .<GankIoWelfareListBean>rxSchedulerHelper());
    }
}
