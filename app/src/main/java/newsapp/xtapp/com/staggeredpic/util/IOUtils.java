package newsapp.xtapp.com.staggeredpic.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by TLQ on 2017/8/31.
 * <p>
 * IO流工具类
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtil.e("IOException","流异常");
            }
        }
        return true;
    }
}
