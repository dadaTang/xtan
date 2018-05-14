package newsapp.xtapp.com.staggeredpic.contract.gankio;


import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareItemBean;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareListBean;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public interface GankIoWelfareContract {
    abstract class GankIoWelfarePresenter extends BaseTabsContract
            .BaseTabsPresenter<IGankIoWelfareModel, IGankIoWelfareView, GankIoWelfareItemBean> {

    }

    interface IGankIoWelfareModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 获取福利list
         *
         * @param pre_page 每页条数
         * @param page     当前页
         * @return Observable
         */
        Observable<GankIoWelfareListBean> getWelfareList(int pre_page, int page);
    }

    interface IGankIoWelfareView extends BaseTabsContract.IBaseTabsView<GankIoWelfareItemBean> {
    }
}
