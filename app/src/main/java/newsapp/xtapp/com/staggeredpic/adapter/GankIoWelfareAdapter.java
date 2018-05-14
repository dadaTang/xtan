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
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoWelfareItemBean;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class GankIoWelfareAdapter extends BaseCompatAdapter<GankIoWelfareItemBean, BaseViewHolder>{
    public GankIoWelfareAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public GankIoWelfareAdapter(@LayoutRes int layoutResId, @Nullable List<GankIoWelfareItemBean>
            data) {
        super(layoutResId, data);
    }

    public GankIoWelfareAdapter(@Nullable List<GankIoWelfareItemBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareItemBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .apply(new RequestOptions().placeholder(R.mipmap.img_default_meizi))
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
