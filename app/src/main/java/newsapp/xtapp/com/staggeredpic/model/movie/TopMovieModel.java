package newsapp.xtapp.com.staggeredpic.model.movie;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.movie.TopMovieContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.HotMovieBean;
import newsapp.xtapp.com.staggeredpic.model.http.DoubanApi;


/**
 * Created by Horrarndoo on 2017/10/18.
 * <p>
 */

public class TopMovieModel extends BaseModel implements TopMovieContract.ITopMovieModel {

    @NonNull
    public static TopMovieModel newInstance() {
        return new TopMovieModel();
    }

    @Override
    public Observable<HotMovieBean> getTopMovieList(int start, int count) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getMovieTop250
                (start, count).compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}
