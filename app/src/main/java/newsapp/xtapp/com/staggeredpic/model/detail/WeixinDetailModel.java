package newsapp.xtapp.com.staggeredpic.model.detail;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.detail.WeixinDetailContract;


/**
 * Created by Horrarndoo on 2017/9/21.
 * <p>
 */

public class WeixinDetailModel extends BaseModel implements WeixinDetailContract.IWeixinDetailModel {
    @NonNull
    public static WeixinDetailModel newInstance() {
        return new WeixinDetailModel();
    }
}
