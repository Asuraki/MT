package com.synebo.tools;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 01.03.2016.
 */
public class Util {

    public static String convertDateToString(Calendar calendar) {
        return calendar != null ? new SimpleDateFormat("ddMMYY").format(new Date(calendar.getTimeInMillis())) : null;
    }

    public static boolean convertStringToBoolean(String s) {
        if(s == null) return Boolean.parseBoolean(null);
        if(s.toLowerCase().equals("y"))
            return true;
        if(s.toLowerCase().equals("n"))
            return false;
        return Boolean.valueOf(s);
    }

    //DDMMYY
    public static Calendar convertStringToDate(String s) {
        if(s == null) return null;
        Calendar instance = Calendar.getInstance();
        if(s.contains(".")) {
            String[] split = s.split("\\.");
            if(split[2].length() == 2)
                split[2] = 20+split[2];
            instance.set(Integer.valueOf(split[2]), (Integer.valueOf(split[1])-1), Integer.valueOf(split[0]));
            return instance;
        }
        if(s.contains("/")) {
            String[] split = s.split("/");
            if(split[2].length() == 2)
                split[2] = 20+split[2];
            instance.set(Integer.valueOf(split[2]), (Integer.valueOf(split[1])-1), Integer.valueOf(split[0]));
            return instance;
        }
        if (!s.matches("\\d+"))
            return null;
        return null;
    }

    // get last 2 digits (Center Code) in file name
    public static String getLastTwoDigits(String filePath) {
        String tmp = filePath;
        tmp = tmp.replace(".csv", "");
        tmp = tmp.substring(tmp.length()-2);
        return tmp;
    }
}
