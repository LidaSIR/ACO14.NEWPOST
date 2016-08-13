package newpost.utils;

import newpost.model.common.MyDate;

import java.util.Calendar;

public class TimeUtils {
    public static MyDate getCurrentDate() {
        Calendar calendar = GregorianCalendar.getInstance();
        return new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }
}
