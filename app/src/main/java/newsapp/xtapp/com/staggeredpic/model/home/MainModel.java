package newsapp.xtapp.com.staggeredpic.model.home;

import android.support.annotation.NonNull;


import newsapp.xtapp.com.staggeredpic.base.model.BaseModel;
import newsapp.xtapp.com.staggeredpic.contract.main.MainContract;

/**
 * Created by Horrarndoo on 2017/9/11.
 * <p>
 * 主页model
 */

public class MainModel extends BaseModel implements MainContract.IMainModel {

    @NonNull
    public static MainModel newInstance() {
        return new MainModel();
    }

    @Override
    public String[] getTabs() {
        return new String[]{"知乎日报", "热点新闻", "微信精选"};
    }
}
