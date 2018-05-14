package newsapp.xtapp.com.staggeredpic.contract.gankio;

import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.fragment.IBaseFragment;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;
/**
 * Created by Horrarndoo on 2017/10/7.
 * <p>
 */

public interface GankIoMainContract {
    //主页接口
    abstract class GankIoMainPresenter extends BasePresenter<IGankIoMainModel, IGankIoMainView> {
        public abstract void getTabList();
    }

    interface IGankIoMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IGankIoMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
