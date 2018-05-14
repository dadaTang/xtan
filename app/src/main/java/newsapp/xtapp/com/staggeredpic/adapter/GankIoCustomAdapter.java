package newsapp.xtapp.com.staggeredpic.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.config.DBConfig;
import newsapp.xtapp.com.staggeredpic.config.ItemState;
import newsapp.xtapp.com.staggeredpic.model.bean.gankio.GankIoCustomItemBean;
import newsapp.xtapp.com.staggeredpic.util.DBUtils;
import newsapp.xtapp.com.staggeredpic.util.SPUtil;
import newsapp.xtapp.com.staggeredpic.util.StringUtils;
import newsapp.xtapp.com.staggeredpic.view.widgets.RvLoadMoreView;

/**
 * Created by Horrarndoo on 2017/10/13.
 * <p>
 */

public class GankIoCustomAdapter extends BaseMultiItemQuickAdapter<GankIoCustomItemBean,
        BaseViewHolder> {
    private String mImageSize = "?imageView2/0/w/200";

    public GankIoCustomAdapter(@Nullable List<GankIoCustomItemBean> data) {
        super(data);

        setLoadMoreView(new RvLoadMoreView());
        setEnableLoadMore(true);
        openLoadAnimation();

        addItemType(GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL, R.layout
                .item_gank_io_custom_normal);
        addItemType(GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE, R.layout
                .item_gank_io_custom_image);
        addItemType(GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE, R.layout
                .item_gank_io_custom_no_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoCustomItemBean item) {
        initTypeImage(helper, item);

        helper.setText(R.id.tv_item_who, StringUtils.isEmpty(item.getWho()) ? "佚名" : item
                .getWho());
        helper.setText(R.id.tv_item_type, item.getType());
        helper.setText(R.id.tv_item_time, item.getCreatedAt().substring(0, 10));

        switch (helper.getItemViewType()) {
            case GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL:
                helper.setText(R.id.tv_item_title, item.getDesc());
                initTitleColor(helper, item);
                if (item.getImages() != null) {
                    if (item.getImages().size() > 0 && !TextUtils.isEmpty(item.getImages().get(0)))
                        Glide.with(mContext).asBitmap().load(item.getImages().get(0) + mImageSize)

                                .into((ImageView) helper.getView(R.id.iv_item_image));
                }
                break;
            case GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE:
                Glide.with(mContext).asBitmap()
                        .load(item.getUrl())
                        .apply(new RequestOptions().centerCrop()
                                .placeholder(R.mipmap.img_default_meizi))
                        .into((ImageView) helper.getView(R.id.iv_item_image));
                break;
            case GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE:
                helper.setText(R.id.tv_item_title, item.getDesc());
                initTitleColor(helper, item);
                break;
            default:
                break;
        }
    }

    private void initTitleColor(BaseViewHolder helper, GankIoCustomItemBean item) {
        if (DBUtils.getDB(mContext).isRead(DBConfig.TABLE_GANKIO_CUSTOM, item.getType() + item
                .get_id(), ItemState.STATE_IS_READ)) {
            helper.setTextColor(R.id.tv_item_title, Color.GRAY);
        } else {
            if (SPUtil.getNightModel(mContext)) {
                helper.setTextColor(R.id.tv_item_title, Color.WHITE);
            } else {
                helper.setTextColor(R.id.tv_item_title, Color.BLACK);
            }
        }
    }

    private void initTypeImage(BaseViewHolder helper, GankIoCustomItemBean item) {
        switch (item.getType()) {
            case "福利":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_welfare);
                break;
            case "Android":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_android);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
                break;
            case "瞎推荐":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuijian);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuozhan);
                break;
            case "App":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_app);
                break;
        }
    }
}
