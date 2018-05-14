package newsapp.xtapp.com.staggeredpic.contract.movie;


import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.activity.IBaseActivity;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.MovieDetailBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.PersonBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.SubjectsBean;

/**
 * Created by Horrarndoo on 2017/10/18.
 * <p>
 */

public interface MovieDetailContract {
    abstract class MovieDetailPresenter extends BasePresenter<IMovieDetailModel,
                IMovieDetailView> {
        /**
         * 加载电影详情
         *
         * @param id 电影id
         */
        public abstract void loadMovieDetail(String id);

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         */
        public abstract void onItemClick(int position, PersonBean item);

        /**
         * header点击事件
         * @param bean bean
         */
        public abstract void onHeaderClick(SubjectsBean bean);
    }

    interface IMovieDetailModel extends IBaseModel {
        /**
         * 获取电影详情
         *
         * @param id 电影id
         * @return 电影详情
         */
        Observable<MovieDetailBean> getMovieDetail(String id);
    }

    interface IMovieDetailView extends IBaseActivity {
        /**
         * 显示电影详情
         *
         * @param bean 电影详情bean
         */
        void showMovieDetail(MovieDetailBean bean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
