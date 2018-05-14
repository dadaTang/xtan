package newsapp.xtapp.com.staggeredpic.model.detail;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.detail.GankIoDetailContract;


/**
 * Created by TLQ on 2017/10/11.
 * <p>
 */

public class GankIoDetailModel extends BaseModel implements GankIoDetailContract
        .IGankIoDetailModel {

    @NonNull
    public static GankIoDetailModel newInstance() {
        return new GankIoDetailModel();
    }
}
