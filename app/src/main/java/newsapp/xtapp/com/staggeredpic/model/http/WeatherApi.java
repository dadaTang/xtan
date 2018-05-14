package newsapp.xtapp.com.staggeredpic.model.http;

import io.reactivex.Flowable;
import newsapp.xtapp.com.staggeredpic.model.bean.weather.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String HOST = "https://free-api.heweather.com/";

    /**
     * 天气预报
     *
     * @param location
     * @param key
     * @return
     */
    @GET("s6/weather/now")
    //没有数据就填 . 或者 /
    Flowable<WeatherBean> getWeather(@Query("location") String location, @Query("key") String key);

}
