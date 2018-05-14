package newsapp.xtapp.com.staggeredpic.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;


/**
 * Created by Horrarndoo on 2017/9/7.
 * <p>
 */

public class HomeRootFragment extends BaseFragment {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();
        HomeRootFragment fragment = new HomeRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }
}
