package newsapp.xtapp.com.staggeredpic.util.imageutils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import newsapp.xtapp.com.staggeredpic.R;

/**
 * Created by TLQ on 2018/2/2.
 * 图片加载
 */

public class ImageLoader {
    /**
     * 默认模式加载
     */
    public static void loadDefault(Context context, ImageView imageView) {
        Glide.with(context)
                .load(R.drawable.stackblur_default)
                .apply(new RequestOptions().centerCrop()
                        .placeholder(R.drawable.stackblur_default)
                        .error(R.drawable.stackblur_default)
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void loadAll(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .apply(new RequestOptions().centerCrop()
                        .placeholder(R.drawable.stackblur_default)
                        .error(R.drawable.stackblur_default)
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void loadAll(Context context, int imgRes, ImageView imageView) {
        Glide.with(context)
                .load(imgRes)
                .apply(new RequestOptions().centerCrop()
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))

                .into(imageView);
    }

    public static void loadAllNoPlaceHolder(Context context, String imgUrl, int imgRes, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .apply(new RequestOptions().centerCrop()
                        .error(imgRes)
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageView);
    }

    public static void loadAllNoPlaceHolder(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context)
                .load(imgUrl)
                .apply(new RequestOptions().centerCrop()
                        .priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(imageView);
    }

    public static void loadAllAsBitmap(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .apply(new RequestOptions().priority(Priority.LOW)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC))
                .into(imageView);
    }

    public static void loadAllAsBitmap(Context context, String imgUrl, int imgRes, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .apply(new RequestOptions().centerCrop()
                        .placeholder(imgRes)
                        .error(imgRes)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .priority(Priority.LOW))
                .into(imageView);
    }


}
