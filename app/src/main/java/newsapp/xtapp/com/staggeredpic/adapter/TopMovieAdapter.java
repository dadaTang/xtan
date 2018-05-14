package newsapp.xtapp.com.staggeredpic.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.model.bean.douban.movie.child.SubjectsBean;

/**
 * Created by Horrarndoo on 2017/10/18.
 * <p>
 */

public class TopMovieAdapter extends BaseCompatAdapter<SubjectsBean, BaseViewHolder> {

    public TopMovieAdapter(@LayoutRes int layoutResId, @Nullable List<SubjectsBean> data) {
        super(layoutResId, data);
    }

    public TopMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(data);
    }

    public TopMovieAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_top_moive_name, item.getTitle());
        helper.setText(R.id.tv_top_moive_rate, String.valueOf(item.getRating().getAverage()));
        Glide.with(mContext).load(item.getImages().getLarge())
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(new RequestOptions().placeholder(R.mipmap.img_default_movie))
                .into((ImageView) helper.getView(R.id
                .iv_top_moive_photo));
    }
}
