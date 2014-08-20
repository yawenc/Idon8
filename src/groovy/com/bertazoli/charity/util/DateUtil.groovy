package com.bertazoli.charity.util
/**
 * Created by vitor on 10/08/14.
 */
class DateUtil {
    public static Date toBeginingOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance()
        cal.setTime(date)
        cal.set(Calendar.HOUR_OF_DAY, 00)
        cal.set(Calendar.MINUTE, 00)
        cal.set(Calendar.SECOND, 00)
        cal.set(Calendar.MILLISECOND, 00)
        return cal.getTime()
    }

    public static Date toEndOfTheDay(Date date) {
        Calendar cal = Calendar.getInstance()
        cal.setTime(date)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return cal.getTime()
    }
}
