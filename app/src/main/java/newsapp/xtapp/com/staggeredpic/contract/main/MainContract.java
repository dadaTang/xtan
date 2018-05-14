package newsapp.xtapp.com.staggeredpic.contract.main;

import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.fragment.IBaseFragment;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;

public interface MainContract {


    //主页接口
    abstract class MainPresenter extends BasePresenter<IMainModel, IMainView> {
        public abstract void getTabList();
    }
    interface IMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }

}
