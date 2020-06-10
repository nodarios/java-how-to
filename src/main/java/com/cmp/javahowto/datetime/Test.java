package com.cmp.javahowto.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {

    /**
     * Java's java.util.Date and java.sql.Timestamp do not store timezone, They always track time in UTC.
     * <p>
     * When parsing a date string with a timezone, the string is converted from the given timezone to UTC.
     * When parsing a date string without a timezone, the string is converted from the JVM's default timezone to UTC,
     * unless another timezone has been explicitly given to the date parser (commonly SimpleDateFormat).
     * <p>
     * When displaying (aka formatting) a Date/Timestamp,
     * it will be shown in the JVM's default timezone, unless otherwise specified.
     */

    private static Timestamp convertToTimestamp(String time) throws ParseException {
        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS X";
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS Z";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //System.out.println(sdf.getTimeZone());
        //sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tbilisi"));
        Date date = sdf.parse(time);
        return new Timestamp(date.getTime());
    }

    public static void main(String[] args) throws ParseException {
        //System.out.println(convertToTimestamp("2016-01-31 10:00:00.123 +03:00"));
        System.out.println(convertToTimestamp("2016-01-31 10:00:00.123 GET"));
        System.out.println(convertToTimestamp("2016-01-31 10:00:00.123 UTC"));
    }

}
