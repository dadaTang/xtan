package newsapp.xtapp.com.staggeredpic.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;


import com.orhanobut.logger.Logger;

import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.util.ToastUtils;

/**
 * Created by TLQ on 2017/4/6.
 * <p>
 * Mvp Activity基类
 */
public abstract class BaseMVPActivity<P extends BasePresenter> extends
        BaseActivity implements IBaseActivity {
    /**
     * presenter 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * 初始化数据
     * <p>
     * 子类可以复写此方法初始化子类数据
     */
    protected void initData() {
        super.initData();
        mPresenter = (P) initPresenter();
        if (mPresenter != null) {
            mPresenter.attachMV(this);
            Logger.d("attach M V success.");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
            //Logger.d("detach M V success.");
        }
    }


    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    public void showWaitDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void hideWaitDialog() {
        hideProgressDialog();
    }


    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public void hideKeybord() {
        hiddenKeyboard();
    }

    @Override
    public void back() {
        super.onBackPressedSupport();
    }


}
