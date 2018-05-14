package newsapp.xtapp.com.staggeredpic.model.http;


import io.reactivex.Observable;
import newsapp.xtapp.com.staggeredpic.model.bean.wangyi.WangyiNewsListBean;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 */

public interface WangyiApi {
    public final String HOST = "http://c.m.163.com";

    @GET("/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<WangyiNewsListBean> getNewsList(@Path("id") int id);

    @GET("/nc/article/{id}/full.html")
    Observable<ResponseBody> getNewsDetail(@Path("id") String id);
}
