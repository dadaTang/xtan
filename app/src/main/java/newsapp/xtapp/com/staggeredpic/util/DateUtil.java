package newsapp.xtapp.com.staggeredpic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间转化工具类
 * Created by TLQ on 2017/9/14.
 */

public class DateUtil {

    /**
     * 毫秒值转化为时间
     *
     * @param time 时间毫秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String Long2String(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 时间转化为字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String Date2String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 字符串转化为时间
     *
     * @param time 2010-11-20 11:10:10
     * @return Date
     */
    public static Date String2Date(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 秒数转分钟
     *
     * @param seconds
     * @return
     */
    public static String second2Minute(int seconds) {
        int minutes = seconds / 60;
        String stringLast;
        if (minutes <= 9) {
            stringLast = "0" + minutes;
        } else {
            stringLast = "" + minutes;
        }
        int remainingSeconds = seconds % 60;
        String durationString;
        if (remainingSeconds < 10) {
            durationString = "0" + remainingSeconds;

        } else {
            durationString = "" + remainingSeconds;

        }
        return stringLast + "′ " + durationString + "″";
    }
    /**
     * 获取当前的时间戳，时区为北京
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        //时间戳的格式必须为 yyyy-MM-dd HH:mm:ss
        String timestamp = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timestamp = format.format(new Date());
        return timestamp;
    }

    /**
     * 获取当前的时间戳，时区为北京
     *
     * @return
     */
    public static String getCurrentTime(long times) {
        //时间戳的格式必须为 yyyy-MM-dd HH:mm:ss
        Date date = new Date(Long.valueOf(times));
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        LogUtil.e("timetimetimetimetimetimetime为：" + time);

        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date());

        return time;
    }

    //法国时间：东一区
    public static String getDateTimeByGMT(int timeZone) {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (timeZone) {
            case 1:
                dff.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                break;
            case 8:
                dff.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                //LogUtils.i("采用东八区时区");
                break;
        }

        String time = dff.format(new Date());
        //LogUtils.i("东八区时区时间为--》》" + time);
        return time;
    }
}
