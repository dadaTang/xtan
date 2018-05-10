package newsapp.xtapp.com.staggeredpic.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.config.GlideApp;

/**
 * Created by TLQ on 2018/2/2.
 */

public class ImageLoader {

    public static void loadDefault(Context context, ImageView imageView) {
        GlideApp.with(context)
                .load(R.drawable.stackblur_default)
                .centerCrop()
                .placeholder(R.drawable.stackblur_default)
                .error(R.drawable.stackblur_default)
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    public static void loadAll(Context context, String imgUrl, ImageView imageView) {
        GlideApp.with(context)
                .load(imgUrl)
                .centerCrop()
                .placeholder(R.drawable.stackblur_default)
                .error(R.drawable.stackblur_default)
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    public static void loadAll(Context context, int imgRes, ImageView imageView) {
        GlideApp.with(context)
                .load(imgRes)
                .centerCrop()
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadAllNoPlaceHolder(Context context, String imgUrl, int imgRes, ImageView imageView) {
        GlideApp.with(context)
                .load(imgUrl)
                .centerCrop()
                .error(imgRes)
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static void loadAllNoPlaceHolder(Context context, String imgUrl, ImageView imageView) {
        GlideApp.with(context)
                .load(imgUrl)
                .centerCrop()
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    public static void loadAllAsBitmap(Context context, String imgUrl, ImageView imageView) {
        GlideApp.with(context)
                .asBitmap()
                .load(imgUrl)
                .priority(Priority.LOW)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageView);
    }

    public static void loadAllAsBitmap(Context context, String imgUrl, int imgRes, ImageView imageView) {
        GlideApp.with(context)
                .asBitmap()
                .load(imgUrl)
                .centerCrop()
                .placeholder(imgRes)
                .error(imgRes)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.LOW)
                .into(imageView);
    }
    /**
     * 获取模糊虚化的bitmap
     *
     * @param context
     * @param bitmap  要模糊的图片
     * @param radius  模糊等级 >=0 && <=25
     * @return
     */
    public static Bitmap getBlurBitmap(Context context, Bitmap bitmap, int radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return blurBitmap(context, bitmap, radius);
        }
        return bitmap;
    }

    /**
     * android系统的模糊方法
     *
     * @param bitmap 要模糊的图片
     * @param radius 模糊等级 >=0 && <=25
     */
    public static Bitmap blurBitmap(Context context, Bitmap bitmap, int radius) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //Let's create an empty bitmap with the same size of the bitmap we want to blur
            Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap
                    .Config.ARGB_8888);
            //Instantiate a new Renderscript
            RenderScript rs = RenderScript.create(context);
            //Create an Intrinsic Blur Script using the Renderscript
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
            Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
            Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
            //Set the radius of the blur
            blurScript.setRadius(radius);
            //Perform the Renderscript
            blurScript.setInput(allIn);
            blurScript.forEach(allOut);
            //Copy the final bitmap created by the out Allocation to the outBitmap
            allOut.copyTo(outBitmap);
            //recycle the original bitmap
            bitmap.recycle();
            //After finishing everything, we destroy the Renderscript.
            rs.destroy();
            return outBitmap;
        } else {
            return bitmap;
        }
    }






}
