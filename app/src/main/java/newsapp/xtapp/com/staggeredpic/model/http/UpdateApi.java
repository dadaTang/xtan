package newsapp.xtapp.com.staggeredpic.model.http;
import io.reactivex.Flowable;
import newsapp.xtapp.com.staggeredpic.constant.Constants;
import newsapp.xtapp.com.staggeredpic.model.bean.update.UpdateBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpdateApi {
    /**
     * 更新地址json格式：
     * {"name":"XT News",
     * "version":"6","changelog":"微信新闻key更新","updated_at":1522835868,
     * "versionShort":"1.5",
     * "build":"6",
     * "installUrl":"http://download.fir.im/v2/app/install/59e01d00959d691da60001b0?download_token=98c5bb355d23be3821ae5721490f8296\u0026source=update",
     * "install_url":"http://download.fir.im/v2/app/install/59e01d00959d691da60001b0?download_token=98c5bb355d23be3821ae5721490f8296\u0026source=update",
     * "direct_install_url":"http://download.fir.im/v2/app/install/59e01d00959d691da60001b0?download_token=98c5bb355d23be3821ae5721490f8296\u0026source=update",
     * "update_url":"http://fir.im/Ency","binary":{"fsize":4617580}}
     */
    //下载地址
    //https://pro-app-qn.fir.im/9a247b405266533412cf15a137058b8d18d69489.apk?attname=Ency_1.5_release.apk_1.5.apk&e=1525773109&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:sqNtDHiJddV95QJRHLWgSd8DiWM=
    //http://download.fir.im/v2/app/install/59e01d00959d691da60001b0?download_token=98c5bb355d23be3821ae5721490f8296\u0026source=update

    String HOST = "http://api.fir.im/apps/latest/";

    /**
     * 获取最新版本信息
     *
     * @param api_token
     * @return
     */
    @GET(Constants.FIR_IM_ID)
    Flowable<UpdateBean> getVersionInfo(@Query("api_token") String api_token);

}
