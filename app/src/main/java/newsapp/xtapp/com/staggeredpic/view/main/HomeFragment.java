package newsapp.xtapp.com.staggeredpic.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.adapter.FragmentAdapter;
import newsapp.xtapp.com.staggeredpic.anim.ToolbarAnimManager;
import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.activity.BaseActivity;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseMVPFragment;
import newsapp.xtapp.com.staggeredpic.constant.BundleKeyConstant;
import newsapp.xtapp.com.staggeredpic.constant.TabFragmentIndex;
import newsapp.xtapp.com.staggeredpic.contract.main.MainContract;
import newsapp.xtapp.com.staggeredpic.presenter.main.MainPresenter;
import newsapp.xtapp.com.staggeredpic.util.SPUtil;
import newsapp.xtapp.com.staggeredpic.view.detail.WebViewLoadActivity;
import newsapp.xtapp.com.staggeredpic.view.main.tabs.WangyiFragment;
import newsapp.xtapp.com.staggeredpic.view.main.tabs.WeixinFragment;
import newsapp.xtapp.com.staggeredpic.view.main.tabs.ZhihuFragment;

/**
 * Created by Horrarndoo on 2017/10/8.
 * <p>
 */

public class HomeFragment extends BaseMVPFragment<MainContract.MainPresenter>
        implements MainContract.IMainView {

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.fab_download)
    FloatingActionButton fabDownload;

    protected OnOpenDrawerLayoutListener onOpenDrawerLayoutListener;
    private List<Fragment> fragments;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenDrawerLayoutListener) {
            onOpenDrawerLayoutListener = (OnOpenDrawerLayoutListener) context;
        }
        fragments = new ArrayList<>();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onOpenDrawerLayoutListener = null;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTabList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        toolbar.setTitle("首页");
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOpenDrawerLayoutListener != null) {
                    onOpenDrawerLayoutListener.onOpen();
                }
            }
        });
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabDownload.show();
                } else {
                    fabDownload.hide();
                }
            }
        });
        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, "Yizhi");
                bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                        "https://github.com/Horrarndoo/YiZhi");
                startNewActivity(WebViewLoadActivity.class, bundle);
            }
        });
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.getMenu().findItem(R.id.night).setChecked(SPUtil.getNightModel(mContext));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.night:
                        item.setChecked(!item.isChecked());
                        SPUtil.setNightModel(mContext, item.isChecked());
                        ((BaseActivity) mActivity).reload();
                        break;
                }
                return false;
            }
        });

        ToolbarAnimManager.animIn(mContext, toolbar);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MainPresenter.newInstance();
    }

    @Override
    public void showTabList(String[] tabs) {
        Logger.w(Arrays.toString(tabs));
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case TabFragmentIndex.TAB_ZHIHU_INDEX:
                    fragments.add(ZhihuFragment.newInstance());
                    break;
                case TabFragmentIndex.TAB_WANGYI_INDEX:
                    fragments.add(WangyiFragment.newInstance());
                    break;
                case TabFragmentIndex.TAB_WEIXIN_INDEX:
                    fragments.add(WeixinFragment.newInstance());
                    break;
                default:
                    fragments.add(ZhihuFragment.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        vpFragment.setCurrentItem(TabFragmentIndex.TAB_ZHIHU_INDEX);//要设置到viewpager.setAdapter后才起作用
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(TabFragmentIndex.TAB_ZHIHU_INDEX);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }
    }

    /**
     * fragment打开DrawerLayout监听
     */
    public interface OnOpenDrawerLayoutListener {
        void onOpen();
    }
}
