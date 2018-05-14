package newsapp.xtapp.com.staggeredpic.presenter.book;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.contract.book.BookMainContract;
import newsapp.xtapp.com.staggeredpic.model.book.BookMainModel;


/**
 * Created by Horrarndoo on 2017/10/21.
 * <p>
 */

public class BookMainPresenter extends BookMainContract.BookMainPresenter {
    @NonNull
    public static BookMainPresenter newInstance() {
        return new BookMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public BookMainContract.IBookMainModel getModel() {
        return BookMainModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
