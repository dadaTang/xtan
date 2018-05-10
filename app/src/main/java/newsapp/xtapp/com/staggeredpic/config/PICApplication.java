package newsapp.xtapp.com.staggeredpic.config;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 全集自定义Application
 * 初始化数据
 * */
public class PICApplication extends Application {

    private static PICApplication instance;
    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;


    /**
     *
     * */
    public static PICApplication getInstance() {
        if (instance == null) {
            synchronized (PICApplication.class) {
                if (instance == null) {
                    instance = new PICApplication();
                }
            }
        }
        return instance;
    }


    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }


}


