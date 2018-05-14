package newsapp.xtapp.com.staggeredpic.helper.okhttp;


import java.io.File;

import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;
import okhttp3.Cache;

/**
 * Created by TLQ on 2017/9/12.
 * <p>
 */
public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(AppApplicationUtil.getContext().getCacheDir().getAbsolutePath() + File
                .separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
