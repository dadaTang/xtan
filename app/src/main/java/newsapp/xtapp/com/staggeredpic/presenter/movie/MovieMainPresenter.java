package newsapp.xtapp.com.staggeredpic.presenter.movie;

import android.support.annotation.NonNull;
import android.widget.ImageView;


import io.reactivex.functions.Consumer;
import newsapp.xtapp.com.staggeredpic.cache.Cache;
import newsapp.xtapp.com.staggeredpic.contract.movie.MovieMainContract;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.HotMovieBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.SubjectsBean;
import newsapp.xtapp.com.staggeredpic.model.movie.MovieMainModel;
import newsapp.xtapp.com.staggeredpic.view.detail.MovieDetailActivity;
import newsapp.xtapp.com.staggeredpic.view.movie.TopMoiveFragment;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class MovieMainPresenter extends MovieMainContract.MovieMainPresenter {

    @NonNull
    public static MovieMainPresenter newInstance() {
        return new MovieMainPresenter();
    }

    @Override
    public void loadHotMovieList() {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getHotMovieList().subscribe(new Consumer<HotMovieBean>() {
            @Override
            public void accept(HotMovieBean hotMovieBean) throws Exception {
                if (mIView == null)
                    return;

                mIView.updateContentList(hotMovieBean.getSubjects());
                Cache.saveHotMovieCache(hotMovieBean.getSubjects());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    if (mIView.isVisiable())
                        mIView.showToast("Network error.");

                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
                        mIView.showNetworkError();
                    } else {
                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
                    }
                }
            }
        }));
    }

    @Override
    public void onItemClick(int position, SubjectsBean item, ImageView imageView) {
//        Logger.e("position " + position + " is clicked.");
        MovieDetailActivity.start(mIView.getBindActivity(), item, imageView);
    }

    @Override
    public void onHeaderClick() {
        mIView.startNewFragment(TopMoiveFragment.newInstance());
    }

    @Override
    public MovieMainContract.IMovieMainModel getModel() {
        return MovieMainModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
