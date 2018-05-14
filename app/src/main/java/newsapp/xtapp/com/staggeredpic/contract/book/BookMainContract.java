package newsapp.xtapp.com.staggeredpic.contract.book;


import newsapp.xtapp.com.staggeredpic.base.BasePresenter.BasePresenter;
import newsapp.xtapp.com.staggeredpic.base.fragment.IBaseFragment;
import newsapp.xtapp.com.staggeredpic.base.model.IBaseModel;

/**
 * Created by Horrarndoo on 2017/10/21.
 * <p>
 */

public interface BookMainContract {
    //book主页接口
    abstract class BookMainPresenter extends BasePresenter<IBookMainModel, IBookMainView> {
        public abstract void getTabList();
    }

    interface IBookMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IBookMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
