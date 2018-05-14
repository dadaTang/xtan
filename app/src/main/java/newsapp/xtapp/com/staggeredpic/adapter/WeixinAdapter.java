package newsapp.xtapp.com.staggeredpic.adapter;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.model.bean.weixin.WeixinChoiceItemBean;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.SPUtil;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 * 微信精选Adapter
 */

public class WeixinAdapter extends BaseCompatAdapter<WeixinChoiceItemBean, BaseViewHolder> {

    public WeixinAdapter(@LayoutRes int layoutResId, @Nullable List<WeixinChoiceItemBean> data) {
        super(layoutResId, data);
    }

    public WeixinAdapter(@Nullable List<WeixinChoiceItemBean> data) {
        super(data);
    }

    public WeixinAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeixinChoiceItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_WEIXIN, item.getId(), ItemState
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
        Glide.with(mContext).load(item.getFirstImg())
                .transition(new DrawableTransitionOptions().crossFade())
                .into((ImageView) helper.getView(R
                .id.iv_item_image));
    }
}
