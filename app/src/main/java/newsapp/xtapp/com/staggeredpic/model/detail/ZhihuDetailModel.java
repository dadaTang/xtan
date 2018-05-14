package newsapp.xtapp.com.staggeredpic.model.detail;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.detail.ZhihuDetailContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.zhihu.ZhihuDailyDetailBean;
import newsapp.xtapp.com.staggeredpic.model.http.ZhihuApi;


/**
 * Created by Horrarndoo on 2017/9/13.
 * <p>
 */

public class ZhihuDetailModel extends BaseModel implements ZhihuDetailContract.IZhihuDetailModel {

    @NonNull
    public static ZhihuDetailModel newInstance() {
        return new ZhihuDetailModel();
    }

    @Override
    public Observable<ZhihuDailyDetailBean> getDailyDetail(String id) {
        return RetrofitCreateHelper.createApi(ZhihuApi.class, ZhihuApi.HOST).getZhihuDailyDetail
                (id).compose(RxHelper.<ZhihuDailyDetailBean>rxSchedulerHelper());
    }
}
