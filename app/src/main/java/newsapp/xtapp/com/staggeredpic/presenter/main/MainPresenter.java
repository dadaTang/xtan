package newsapp.xtapp.com.staggeredpic.presenter.main;

import android.support.annotation.NonNull;


import newsapp.xtapp.com.staggeredpic.contract.main.MainContract;
import newsapp.xtapp.com.staggeredpic.model.home.MainModel;

/**
 */

public class MainPresenter extends MainContract.MainPresenter {
    @NonNull
    public static MainPresenter newInstance() {
        return new MainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public MainContract.IMainModel getModel() {
        return MainModel.newInstance();
    }

    @Override
    public void onStart() {
    }


}