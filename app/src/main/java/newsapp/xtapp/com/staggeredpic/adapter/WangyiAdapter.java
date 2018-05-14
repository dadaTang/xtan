package newsapp.xtapp.com.staggeredpic.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.model.bean.wangyi.WangyiNewsItemBean;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.SPUtil;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 * 网易新闻Adapter
 */

public class WangyiAdapter extends BaseCompatAdapter<WangyiNewsItemBean, BaseViewHolder> {

    public WangyiAdapter(@LayoutRes int layoutResId, @Nullable List<WangyiNewsItemBean> data) {
        super(layoutResId, data);
    }

    public WangyiAdapter(@Nullable List<WangyiNewsItemBean> data) {
        super(data);
    }

    public WangyiAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WangyiNewsItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_WANGYI, item.getDocid(), ItemState
                .STATE_IS_READ)) {
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        } else {
            if (SPUtil.getNightModel(mContext)) {
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            } else {
                helper.setTextColor(R.id.tv_item_title, Color.BLACK);
            }
        }
        helper.setText(R.id.tv_item_title, item.getTitle());
        helper.setText(R.id.tv_item_who, item.getSource());
        helper.setText(R.id.tv_item_time, item.getPtime());
        Glide.with(mContext).load(item.getImgsrc())
                .apply(new RequestOptions().error(R.drawable.stackblur_default)
                        .placeholder(R.drawable.stackblur_default))
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
