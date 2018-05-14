package newsapp.xtapp.com.staggeredpic.model.movie;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.movie.MovieDetailContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.MovieDetailBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.PersonBean;
import newsapp.xtapp.com.staggeredpic.model.http.DoubanApi;


/**
 * Created by Horrarndoo on 2017/10/18.
 * <p>
 */

public class MovieDetailModel extends BaseModel implements MovieDetailContract.IMovieDetailModel {
    @NonNull
    public static MovieDetailModel newInstance() {
        return new MovieDetailModel();
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getMovieDetail(id)
                .compose(RxHelper.<MovieDetailBean>rxSchedulerHelper())
                .map(new Function<MovieDetailBean, MovieDetailBean>() {
                    @Override
                    public MovieDetailBean apply(MovieDetailBean bean) throws Exception {
                        //返回的数据没有person type，根据数组类型指定
                        for (PersonBean bean1 : bean.getCasts()) {
                            bean1.setType("主演");
                        }
                        for (PersonBean bean2 : bean.getDirectors()) {
                            bean2.setType("导演");
                        }
                        return bean;
                    }
                });
    }
}
