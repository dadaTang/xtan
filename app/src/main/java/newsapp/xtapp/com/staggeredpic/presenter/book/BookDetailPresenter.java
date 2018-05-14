package newsapp.xtapp.com.staggeredpic.presenter.book;

import android.os.Bundle;
import android.support.annotation.NonNull;


import io.reactivex.functions.Consumer;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.contract.book.BookDeatilContract;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.book.BookDetailBean;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.book.BookItemBean;
import newsapp.xtapp.com.staggeredpic.model.book.BookDetailModel;
import newsapp.xtapp.com.staggeredpic.view.detail.WebViewLoadActivity;

/**
 * Created by Horrarndoo on 2017/10/23.
 * <p>
 */

public class BookDetailPresenter extends BookDeatilContract.BookDetailPresenter {

    @NonNull
    public static BookDetailPresenter newInstance() {
        return new BookDetailPresenter();
    }

    @Override
    public void loadBookDetail(String id) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getBookDetail(id).subscribe(new Consumer<BookDetailBean>() {
            @Override
            public void accept(BookDetailBean bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.showBookDetail(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("Network error.");
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public void onHeaderClick(BookItemBean bean) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, bean.getTitle());
        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL, bean.getAlt());
        mIView.startNewActivity(WebViewLoadActivity.class, bundle);
    }

    @Override
    public BookDeatilContract.IBookDetailModel getModel() {
        return BookDetailModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
