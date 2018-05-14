package newsapp.xtapp.com.staggeredpic.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

/**
 * 全集自定义Application
 * 初始化数据
 */
public class PICApplication extends Application {
    //这个key是自己在聚合数据申请的key，需要自己去聚合数据申请
    public static final String JU_HE_APP_KEY = "799b785ba7b97223be80534651dd0d63";

    private static PICApplication instance;
    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;
    public static PICApplication app;


    @Override
    public void onCreate() {
        MobSDK.init(this);
        super.onCreate();
        app = this;
        //初始化屏幕宽高

        CrashReport.initCrashReport(getApplicationContext(), "c73ecc54da", false);
    }

    /**
     *获取实例对象
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
    public static synchronized PICApplication getContext() {
        return (PICApplication) context;
    }
   /* public static Context getContext() {
        return context;
    }*/

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


