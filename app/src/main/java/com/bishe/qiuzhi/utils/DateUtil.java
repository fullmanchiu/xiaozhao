package com.bishe.qiuzhi.utils;

import android.content.Context;
import android.util.Log;

import com.bishe.qiuzhi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期相关工具类
 * unix时间戳与文字的转换
 */
public class DateUtil {
    private static Calendar calendar;

    static {
        calendar = Calendar.getInstance();
    }

    public static Date getCurrentYearFirstDay() {
        return geYearFirstDay(calendar.get(Calendar.YEAR));
    }

    private static Date geYearFirstDay(int year) {
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    public static String simpleFormat(String pattern, long timeInMillis) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        Log.d("aaaa", "timeInMillis:" + timeInMillis + "     " + timeInMillis * 1000);
        return format.format(timeInMillis * 1000);
    }

    public static String convertTimeToFormat(Context context, long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return context.getString(R.string.text_time_just_now);
        } else if (time >= 60 && time < 3600) {
            return time / 60 + context.getString(R.string.text_time_minutes_ago);
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + context.getString(R.string.text_time_hours_ago);
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + context.getString(R.string.text_time_days_ago);
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + context.getString(R.string.text_time_months_ago);
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + context.getString(R.string.text_time_years_ago);
        } else {
            return "";
        }
    }
}
