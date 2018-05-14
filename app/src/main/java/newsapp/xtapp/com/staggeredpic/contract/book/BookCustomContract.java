package newsapp.xtapp.com.staggeredpic.contract.book;

import android.widget.ImageView;

import java.util.List;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.fragment.IBaseFragment;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.book.BookItemBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.book.BookListBean;

/**
 * Created by Horrarndoo on 2017/9/20.
 * <p>
 */

public interface BookCustomContract {
    abstract class BookCustomPresenter extends BasePresenter<IBookCustomModel, IBookCustomView> {
        /**
         * 加载最新的book list
         */
        public abstract void loadLatestBookList();

        /**
         * 加载更多book list
         */
        public abstract void loadMoreBookList();

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, BookItemBean item, ImageView imageView);
    }

    interface IBookCustomModel extends IBaseModel {
        /**
         * 根据tag获取图书
         *
         * @param tag   搜索关键字
         * @param start 从多少开始，如从"0"开始
         * @param count 一次请求的数目 最多100
         * @return Observable
         */
        Observable<BookListBean> getBookListWithTag(String tag, int start, int count);
    }

    interface IBookCustomView extends IBaseFragment {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<BookItemBean> list);

        /**
         * 显示网络错误
         */
        void showNetworkError();

        /**
         * 显示加载更多错误
         */
        void showLoadMoreError();

        /**
         * 显示没有更多数据
         */
        void showNoMoreData();

        /**
         * 返回定制book tags
         *
         * @return 定制book tags
         */
        String getBookTags();
    }
}
