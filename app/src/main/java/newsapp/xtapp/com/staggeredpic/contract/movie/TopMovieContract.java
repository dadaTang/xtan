package newsapp.xtapp.com.staggeredpic.contract.movie;

import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.fragment.IBaseFragment;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.HotMovieBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.SubjectsBean;


/**
 * Created by Horrarndoo on 2017/10/18.
 * <p>
 */

public interface TopMovieContract {
    abstract class TopMoivePresenter extends BasePresenter<ITopMovieModel, ITopMovieView> {
        /**
         * 加载Top电影list
         */
        public abstract void loadTopMovieList();

        /**
         * 加载更多Top电影
         */
        public abstract void loadMoreTopMovie();

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, SubjectsBean item, ImageView imageView);
    }

    interface ITopMovieModel extends IBaseModel {
        /**
         * 获取豆瓣电影top250
         *
         * @param start 从多少开始，如从"0"开始
         * @param count 一次请求的数目，如"10"条，最多100
         */
        Observable<HotMovieBean> getTopMovieList(int start, int count);
    }

    interface ITopMovieView extends IBaseFragment {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<SubjectsBean> list);

        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 没有更多数据
         */
        void showNoMoreData();

        /**
         * 显示加载更多失败
         */
        void showLoadMoreError();
    }
}
