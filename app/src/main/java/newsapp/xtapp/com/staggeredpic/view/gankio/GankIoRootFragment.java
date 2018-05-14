package newsapp.xtapp.com.staggeredpic.view.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;

/**
 * Created by Horrarndoo on 2017/10/8.
 * <p>
 */

public class GankIoRootFragment extends BaseFragment {

    public static GankIoRootFragment newInstance() {
        Bundle args = new Bundle();
        GankIoRootFragment fragment = new GankIoRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gank_io;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(GankIoFragment.class) == null) {
            loadRootFragment(R.id.fl_container, GankIoFragment.newInstance());
        }
    }
}
