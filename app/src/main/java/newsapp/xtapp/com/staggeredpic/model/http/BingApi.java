package newsapp.xtapp.com.staggeredpic.model.http;
import io.reactivex.Flowable;
import newsapp.xtapp.com.staggeredpic.model.bean.image.BingBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BingApi {
    String HOST = "https://bing.ioliu.cn/";

    /**
     * Bing随机壁纸
     *
     * @param width
     * @param height
     * @param type
     * @return
     */
    @GET("v1/rand")
    Flowable<BingBean> getBingBean(@Query("w") String width, @Query("h") String height, @Query("type") String type);

}
