package newsapp.xtapp.com.staggeredpic.view.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.OnClick;
import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.fragment.BaseFragment;
import newsapp.xtapp.com.staggeredpic.view.about.AboutActivity;

/**
 * Created by Horrarndoo on 2017/9/26.
 * <p>
 */

public class PersonalLowerFragment extends BaseFragment {

    @BindView(R.id.tv_btn_settings)
    TextView tvBtnSetting;
    @BindView(R.id.tv_btn_about)
    TextView tvBtnAbout;

    public static PersonalLowerFragment newInstance() {
        Bundle args = new Bundle();
        PersonalLowerFragment fragment = new PersonalLowerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_lower;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_btn_settings, R.id.tv_btn_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_settings:
                start(PersonalSettingFragment.newInstance());
                break;
            case R.id.tv_btn_about:
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        //不处理，直接丢给Activity onBackPressedSupport处理
        //若此处要拦截回退逻辑到HomeFragment，直接使用RxBus处理
        return false;
    }
}
