package newsapp.xtapp.com.staggeredpic.model.detail;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.detail.WebViewLoadConaract;


/**
 * Created by TLQ on 2017/10/20.
 * <p>
 */

public class WebViewLoadModel extends BaseModel implements
        WebViewLoadConaract.IWebViewLoadModel {

    @NonNull
    public static WebViewLoadModel newInstance() {
        return new WebViewLoadModel();
    }
}
