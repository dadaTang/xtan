package newsapp.xtapp.com.staggeredpic.view.book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;

/**
 * Created by Horrarndoo on 2017/10/21.
 * <p>
 */

public class BookRootFragment extends BaseFragment {

    public static BookRootFragment newInstance() {
        Bundle args = new Bundle();
        BookRootFragment fragment = new BookRootFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(BookFragment.class) == null) {
            loadRootFragment(R.id.fl_container, BookFragment.newInstance());
        }
    }
}
