package newsapp.xtapp.com.staggeredpic.util;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import newsapp.xtapp.com.staggeredpic.R;


/**
 * 推荐使用SnackBar取代Toast
 */

public class SnackBarUtils {


    public static int red = 0xfff44336;
    public static int green = 0xff4caf50;
    public static int blue = 0xff2195f3;
    public static int orange = 0xffffc107;

    /**
     * 正常显示(黑底白字)
     */
    public static void show(View view, CharSequence msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        ((TextView) (snackbar.getView().findViewById(R.id.snackbar_text))).setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * 设置文字色与背景色
     * backgroundColor：背景颜色码
     * textColor：文字颜色码
     */
    public static void show(View view, CharSequence msg, int textColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(backgroundColor);
        ((TextView) (snackbar.getView().findViewById(R.id.snackbar_text))).setTextColor(textColor);
        snackbar.show();
    }

    /**
     * 向Snackbar中添加view
     *
     * @param snackbar
     * @param layoutId
     * @param index    新加布局在Snackbar中的位置
     */
    public static void addView(Snackbar snackbar, int layoutId, int index) {
        View snackbarview = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId, null);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;

        snackbarLayout.addView(add_view, index, p);
    }


}
