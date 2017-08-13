package com.mifpay.toolbox.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhul on 2015/12/10.
 */
public class DateTimeUtils {

    private final ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public String msToString(long ms, String strFormat){
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public Date toDate(String sdate) {
        try {
            if(sdate.length()==10){
                sdate = sdate + " " + "00:00:00";
            }
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据指定格式，将日期类型转换成字符串
     * @param date
     * @param strFormat
     * @return
     */
    public String dateToString(Date date,String strFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期格式的字符串转换成HHmm
     * @param strDate
     * @return
     */
    public String toHHmm(String strDate){
        Date d = toDate(strDate);
        if(null == d){
            return "00:00";
        }else{
            return dateToString(d, "HH:mm");
        }
    }

    /**
     * 获取年份
     * @return
     */
    public int getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     * @return
     */
    public int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //Calendar取月份会从0开始取，所以要加1
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     * @return
     */
    public int getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
