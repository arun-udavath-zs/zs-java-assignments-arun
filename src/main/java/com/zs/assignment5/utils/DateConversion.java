package com.zs.assignment5.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {
    public static Date dateConverter(String inputDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        Date date = dateFormat.parse(inputDate);
        return date;
    }
}
