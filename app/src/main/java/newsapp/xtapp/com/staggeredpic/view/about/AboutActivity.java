package newsapp.xtapp.com.staggeredpic.view.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import newsapp.xtapp.com.staggeredpic.R;
import newsapp.xtapp.com.staggeredpic.base.activity.BaseActivity;
import newsapp.xtapp.com.staggeredpic.util.ResourcesUtils;
import newsapp.xtapp.com.staggeredpic.util.apputils.AppApplicationUtil;

/**
 * Created by Horrarndoo on 2017/9/22.
 * <p>
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTitleBar(toolbar, "关于");
        toolbar.setTitleTextColor(ResourcesUtils.getColor(R.color.md_white));
        Logger.e("tvVersionCode = " + tvVersionCode);
        tvVersionCode.setText(AppApplicationUtil.getVersionName(this));
    }

    @OnClick(R.id.cv_author)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cv_author:
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://github.com/Horrarndoo"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent); //启动浏览器
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
}
