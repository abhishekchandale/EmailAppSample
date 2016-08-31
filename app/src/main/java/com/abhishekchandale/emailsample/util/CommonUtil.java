package com.abhishekchandale.emailsample.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abhishek on 29/8/16.
 */
public class CommonUtil {

    public static String convertDate(long dateInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(dateInMilliseconds));
        return dateString;
    }

}
