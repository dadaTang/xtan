package newsapp.xtapp.com.staggeredpic.model.movie;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.movie.MovieMainContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.HotMovieBean;
import newsapp.xtapp.com.staggeredpic.model.http.DoubanApi;


/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class MovieMainModel extends BaseModel implements MovieMainContract.IMovieMainModel {

    @NonNull
    public static MovieMainModel newInstance() {
        return new MovieMainModel();
    }

    @Override
    public Observable<HotMovieBean> getHotMovieList() {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getHotMovie()
                .compose(RxHelper.<HotMovieBean>rxSchedulerHelper());
    }
}
