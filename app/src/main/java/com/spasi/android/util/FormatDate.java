package com.spasi.android.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Spasi on 1/18/2016.
 */
public class FormatDate {

    /**
     * Indonesia Date Format
     *
     * @param year
     * @param month
     * @param day
     * @param divider
     * @return
     */
    public static String indonesianDateFormat(int year, int month, int day,String divider) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd"+divider+"MM"+divider+"yyyy");
        Calendar mDate = Calendar.getInstance();
        mDate.set(year,month,day);
        return dateFormat.format(mDate.getTime());
    }
}
