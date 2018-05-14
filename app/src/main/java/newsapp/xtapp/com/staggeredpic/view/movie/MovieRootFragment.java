package newsapp.xtapp.com.staggeredpic.view.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;


/**
 * Created by Horrarndoo on 2017/9/23.
 * <p>
 */

public class MovieRootFragment extends BaseFragment {

    public static MovieRootFragment newInstance() {
        Bundle args = new Bundle();
        MovieRootFragment fragment = new MovieRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //Logger.e("onLazyInitView");
        //加载子fragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(MovieFragment.class) == null) {
                loadRootFragment(R.id.fl_container, MovieFragment.newInstance());
            }
        }
    }
}
