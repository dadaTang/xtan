package newsapp.xtapp.com.staggeredpic.view.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportActivity;
import newsapp.xtapp.com.staggeredpic.view.main.MainActivity;

public class SplashActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 2000);

    }
}
