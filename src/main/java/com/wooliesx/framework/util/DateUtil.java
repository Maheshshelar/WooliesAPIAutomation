package com.wooliesx.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {



    public static boolean isSurfDay(String dateStr,String surfDay1,String surfDay2) {
        //format "2020-08-22 06:00:00"
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = simpleDateformat.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            String day = sdf.format(date); // e.g. Thursday, Monday etc
            if (day.equalsIgnoreCase(surfDay1)) {
                return true;

            }
            else if(day.equalsIgnoreCase(surfDay2))  {
                return true;
            }

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(isSurfDay("2020-08-27 06:00:00"));
    }
}
