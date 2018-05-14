package newsapp.xtapp.com.staggeredpic.view.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;


import butterknife.BindView;
import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;

/**
 * Created by Horrarndoo on 2017/9/23.
 * <p>
 */

public class PersonalRootFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl_personal_container_upper)
    FrameLayout flContainerUpper;
    @BindView(R.id.fl_personal_container_lower)
    FrameLayout flContainerLower;

    public static PersonalRootFragment newInstance() {
        Bundle args = new Bundle();
        PersonalRootFragment fragment = new PersonalRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        toolbar.setTitle("我的");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //Logger.e("onLazyInitView");
        //加载子fragment
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(PersonalUpperFragment.class) == null) {
                loadFragment();
            }
        }
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_personal_container_upper, PersonalUpperFragment.newInstance());
        loadRootFragment(R.id.fl_personal_container_lower, PersonalLowerFragment.newInstance());
    }
}
