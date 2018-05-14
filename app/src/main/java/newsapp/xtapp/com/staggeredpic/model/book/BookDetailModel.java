package newsapp.xtapp.com.staggeredpic.model.book;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.book.BookDeatilContract;
import newsapp.xtapp.com.staggeredpic.helper.RetrofitCreateHelper;
import newsapp.xtapp.com.staggeredpic.helper.RxHelper;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.book.BookDetailBean;
import newsapp.xtapp.com.staggeredpic.model.http.DoubanApi;

/**
 * Created by Horrarndoo on 2017/10/23.
 * <p>
 */

public class BookDetailModel extends BaseModel implements BookDeatilContract.IBookDetailModel {

    @NonNull
    public static BookDetailModel newInstance() {
        return new BookDetailModel();
    }

    @Override
    public Observable<BookDetailBean> getBookDetail(String id) {
        return RetrofitCreateHelper.createApi(DoubanApi.class, DoubanApi.HOST).getBookDetail(id)
                .compose(RxHelper.<BookDetailBean>rxSchedulerHelper());
    }
}
