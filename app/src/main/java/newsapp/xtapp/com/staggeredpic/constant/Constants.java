package newsapp.xtapp.com.staggeredpic.constant;



import java.io.File;

import newsapp.xtapp.com.staggeredpic.config.PICApplication;

/**
 * 常量
 * 相关API KEY
 */

public class Constants {

    public static final String ONE_URL = "https://meiriyiwen.com/";

    // 微信精选Key
    public static final String WEIXIN_KEY = "64d19569dafd30288b26e750d8e66e32";

    // bugly APP ID
    public static final String BUGLY_APP_ID = "f527f7694d";

    // 和风天气Key
    public static final String WEATHER_KEY = "9fda56fd906c45f290a0ff84f1518d4a";

    // fir.im API Token
    public static final String FIR_IM_API_TOKEN = "5d7344950f5376a5c68d1f1b611759d7";

    // fir.im ID
    public static final String FIR_IM_ID = "5af82782548b7a65a73fabe0";

    // 开眼视频pdid
    public static final String EYEPETIZER_UDID = "79a95dc6b649489383e976b5b97d129f6d592fad";

    public static final String PATH_DATA = PICApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //通过类别区分收藏的标志
    public static final int TYPE_DEFAULT = 0;

    public static final int TYPE_WECHAT = 1;

    public static final int TYPE_GANK = 2;

    public static final int TYPE_VIDEO = 3;
}
