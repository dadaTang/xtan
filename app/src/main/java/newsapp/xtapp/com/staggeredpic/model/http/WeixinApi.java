package newsapp.xtapp.com.staggeredpic.model.http;

import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.model.bean.weixin.WeixinChoiceListBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Horrarndoo on 2017/9/21.
 * <p>
 */

public interface WeixinApi {
    public static final String HOST = "http://v.juhe.cn";

    @GET("/weixin/query")
    Observable<WeixinChoiceListBean> getWeixinChoiceList(@Query("pno") int page, @Query("ps") int
            ps, @Query("dtype") String dttype, @Query("key") String key);
}
