package newsapp.xtapp.com.staggeredpic.util;

import android.util.Log;

import newsapp.xtapp.com.staggeredpic.BuildConfig;

/**
 * Created by TLQ on 2017/9/20.
 * <p>
 * 日志打印
 */

public class LogUtil {

    public static final String TAG = "XT_TLQ";


    public static void i(String msg) {
        Log.i(TAG, msg != null ? msg : "");
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg != null ? msg : "");
    }

    /**
     * @param msg
     */
    public static void d(String msg) {
        Log.d(TAG, msg != null ? msg : "");
    }

    /**
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        Log.d(tag, msg != null ? msg : "");
    }

    /**
     * @param msg
     */
    public static void e(String msg) {
        Log.e(TAG, msg != null ? msg : "");
    }

    /**
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg != null ? msg : "");
    }

    /**
     * @param msg
     */
    public static void w(String msg) {
        Log.w(TAG, msg != null ? msg : "");
    }

    /**
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        Log.w(tag, msg != null ? msg : "");
    }

    /**
     * @param msg
     */
    public static void v(String msg) {
        Log.v(TAG, msg != null ? msg : "");
    }

    /**
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        Log.v(tag, msg != null ? msg : "");
    }

}
