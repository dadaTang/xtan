package newsapp.xtapp.com.staggeredpic.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by TLQ on 2017/8/31.
 * <p>
 * 读取文件工具类
 */
public class FileUtils {
    private static final String TAG = "FileUtils";


    /**
     * 获取文件名
     */
    public static String getFileName() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder("Log_");
        stringBuilder.append(Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4));
        stringBuilder.append(".txt");
        return stringBuilder.toString();
    }

    /**
     * Convert byte[] to hex string.将byte转换成int，
     * 然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据文件名称和路径，获取sd卡中的文件，以File形式返回byte
     */
    public static File getFile(String fileName, String folder)
            throws IOException {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File pathFile = new File(Environment.getExternalStorageDirectory()
                    + folder);
            // && !pathFile .isDirectory()
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            File file = new File(pathFile, fileName);
            return file;
        }
        return null;
    }

    /**
     * 根据文件名称和路径，获取sd卡中的文件，判断文件是否存在，存在返回true
     */
    public static Boolean checkFile(String fileName, String folder)
            throws IOException {

        File targetFile = getFile(fileName, folder);

        if (!targetFile.exists()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore
                    .Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    /**
     * 获取文件大小
     * */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 复制文件（字节流）
     */
    public static void copyFile(File sourcefile, File targetFile) {
        FileInputStream input = null;
        BufferedInputStream inbuff = null;
        FileOutputStream out = null;
        BufferedOutputStream outbuff = null;

        try {

            input = new FileInputStream(sourcefile);
            inbuff = new BufferedInputStream(input);

            out = new FileOutputStream(targetFile);
            outbuff = new BufferedOutputStream(out);

            byte[] b = new byte[1024 * 5];
            int len = 0;
            while ((len = inbuff.read(b)) != -1) {
                outbuff.write(b, 0, len);
            }
            outbuff.flush();
        } catch (Exception ex) {
        } finally {
            try {
                if (inbuff != null)
                    inbuff.close();
                if (outbuff != null)
                    outbuff.close();
                if (out != null)
                    out.close();
                if (input != null)
                    input.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * 保存文件（字符流）
     */
    public static boolean saveFile(File dic, String fileName, String msg) {

        File file = new File(dic, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 得到手机的缓存目录
     *
     * @param context
     * @return
     */
    public static File getCacheDir(Context context) {
        Log.i("getCacheDir", "cache sdcard state: " + Environment.getExternalStorageState());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
                return cacheDir;
            }
        }

        File cacheDir = context.getCacheDir();
        Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
        return cacheDir;
    }

    /**
     * @return 创建缓存目录
     */
    public static File getcacheDirectory() {
        File file = new File(AppApplicationUtil.getContext().getExternalCacheDir(), "RxCache");
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Log.e("file", "文件不存在  创建文件    " + b);
        } else {
            Log.e("file", "文件存在");
        }
        return file;
    }

    /**
     * 关闭IO
     *
     * @param closeable closeable
     */
    public static void closeIO(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException e) {
            LogUtil.e("流异常");
        }
    }

}


