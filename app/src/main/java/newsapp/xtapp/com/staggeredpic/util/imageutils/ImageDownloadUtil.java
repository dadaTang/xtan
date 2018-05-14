package newsapp.xtapp.com.staggeredpic.util.imageutils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import newsapp.xtapp.com.staggeredpic.util.MD5Utils;

public class ImageDownloadUtil {

    /**
     * 保存图片到本机
     *
     * @param context            context
     * @param fileName           文件名
     * @param file               file
     * @param saveResultCallback 保存结果callback
     */
    public static void saveImage(final Context context, final String fileName, final File file,
                                 final SaveResultCallback saveResultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File appDir = new File(Environment.getExternalStorageDirectory(), "yizhi");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String saveFileName = "yizhi_pic";
                if (fileName.contains(".png") || fileName.contains(".gif")) {
                    String fileFormat = fileName.substring(fileName.lastIndexOf("."));
                    saveFileName = MD5Utils.getMD5("yizhi_pic" + fileName) + fileFormat;
                } else {
                    saveFileName = MD5Utils.getMD5("yizhi_pic" + fileName) + ".png";
                }
                saveFileName = saveFileName.substring(20);//取前20位作为SaveName
                File savefile = new File(appDir, saveFileName);
                try {
                    InputStream is = new FileInputStream(file);
                    FileOutputStream fos = new FileOutputStream(savefile);
                    byte[] buffer = new byte[1024 * 1024];//1M缓冲区
                    int count = 0;
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.close();
                    is.close();
                    saveResultCallback.onSavedSuccess();
                } catch (FileNotFoundException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                } catch (IOException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                }
                //保存图片后发送广播通知更新数据库
                Uri uri = Uri.fromFile(savefile);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            }
        }).start();
    }

    /**
     * 保存Bitmap到本机
     *
     * @param context            context
     * @param fileName           bitmap文件名
     * @param bmp                bitmap
     * @param saveResultCallback 保存结果callback
     */
    public static void saveBitmap(final Context context, final String fileName, final Bitmap bmp,
                                  final SaveResultCallback
                                          saveResultCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File appDir = new File(Environment.getExternalStorageDirectory(), "yizhi");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                //                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置以当前时间格式为图片名称
                String saveFileName = MD5Utils.getMD5("yizhi_pic" + fileName) + ".png";
                saveFileName = saveFileName.substring(20);//取前20位作为SaveName
                File file = new File(appDir, saveFileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    saveResultCallback.onSavedSuccess();
                } catch (FileNotFoundException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                } catch (IOException e) {
                    saveResultCallback.onSavedFailed();
                    e.printStackTrace();
                }
                //保存图片后发送广播通知更新数据库
                Uri uri = Uri.fromFile(file);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            }
        }).start();
    }

    public interface SaveResultCallback {
        void onSavedSuccess();

        void onSavedFailed();
    }

}
