package com.liao27.utils;

import org.joda.time.*;

import java.util.Date;

/**
 * 日期工具
 * Created by Administrator on 2017/10/11 0011.
 */
public class DateUtil {

    /**
     * 计算时间周期
     * @param date 时间
     * @return 周期
     */
    public static String betweenWithDate(Date date) {

        if (date != null) {
            DateTime dt1 = new DateTime(date);
            DateTime now = DateTime.now();
            int year = Years.yearsBetween(dt1, now).getYears();
            if (year > 0) {
                return year + "年前";
            }
            int month = Months.monthsBetween(dt1, now).getMonths();
            if (month > 0) {
                return month + "个月前";
            }
            int week = Weeks.weeksBetween(dt1, now).getWeeks();
            if (week > 0) {
                return week + "周前";
            }
            int day = Days.daysBetween(dt1, now).getDays();
            if (day > 0) {
                return day + "天前";
            }
            int hour = Hours.hoursBetween(dt1, now).getHours();
            if (hour > 0) {
                return hour + "小时前";
            }
            int second = Seconds.secondsBetween(dt1, now).getSeconds();
            if (second > 60) {
                return second / 60 + "分钟前";
            }
            int minute = Minutes.minutesBetween(dt1, now).getMinutes();
            if (minute > 5) {
                return minute + "秒前";
            } else if (minute <= 5) {
                return "刚刚";
            }
        }
        return "";
    }
}
