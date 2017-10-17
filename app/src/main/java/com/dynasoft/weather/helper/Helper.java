package com.dynasoft.weather.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by sundeep on 10/11/17.
 */

public class Helper {

    static String stream = null;

    public Helper(){

    }

    public static String unixTimeStampToTime(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long) unixTimeStamp*1000);
        return dateFormat.format(date);

    }

    public static String getIcon(String icon){
        return String.format("http://openweathermap.org/img/w/%s.png", icon);

    }

    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
