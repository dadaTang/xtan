package newsapp.xtapp.com.staggeredpic.util.apputils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;

import newsapp.xtapp.com.staggeredpic.config.PICApplication;

/**
 * APP应用信息工具类
 * Created by TLQ on 2017/8/15.
 */

public class AppApplicationUtil {
    /**
     * 获取上下文对象
     *
     * @return 上下文对象
     */
    public static Context getContext() {
        return PICApplication.getContext();
    }


    /**
     * 获取APP名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        String appName;
        try {
            PackageManager p = context.getPackageManager();
            PackageInfo info = p.getPackageInfo(context.getPackageName(), 0);
            int labelRes = info.applicationInfo.labelRes;
            appName = context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            appName = "unKnown";
        }
        return appName;
    }

    /**
     * 获取APP版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }


    /**
     * 获取APP版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return verCode;
    }

    /**
     * 获取本地应用大小
     *
     * @param context
     * @return 大小(字节单位) 1MB = 1024KB  1KB = 1024Byte
     */
    public static long getAppSize(Context context) {
        long appSize = 0;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            appSize = new File(applicationInfo.sourceDir).length();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appSize;
    }

    /**
     * 安装应用(兼容Android 7.0及以上)
     * <p>
     * Android N步骤：
     * 1、在res文件夹下面创建xml文件夹，在xml文件夹中创建file_paths.xml文件
     * <paths>
     * <external-path
     * name="XXX名字"
     * path="XXX路径" />
     * </paths>
     * 2、在Manifest中添加
     * <provider
     * android:name="android.support.v4.content.FileProvider"
     * android:authorities="包名.fileProvider"
     * android:exported="false"
     * android:grantUriPermissions="true">
     * <meta-data
     * android:name="android.support.FILE_PROVIDER_PATHS"
     * android:resource="@xml/file_paths" />
     * </provider>
     *
     * @param context
     * @param apkFile
     */
    public static void installApk(Context context, File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", apkFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return PICApplication.getMainThreadId();
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return PICApplication.getHandler();
    }

    /**
     * 判断是否运行在主线程
     *
     * @return true：当前线程运行在主线程
     * fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    /**
     * 运行在主线程
     *
     * @param r 运行的Runnable对象
     */
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }

    /**
     * 显示软键盘
     */
    public static void openSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager
                .HIDE_NOT_ALWAYS);
    }
}