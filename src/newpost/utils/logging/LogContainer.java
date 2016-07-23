package newpost.utils.logging;

import newpost.exceptions.LogException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LogContainer {

    private static List<String> logs;
    private static DateFormat dateFormat;
    private static Calendar cal;
    private static String dateTime;

    static {
        logs = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        cal = Calendar.getInstance();
    }

    public static void logEvent(String event) {
        dateTime = dateFormat.format(cal.getTime());
        logs.add(dateTime + " " + event);
    }

    public static void showAllLogs() throws LogException {
        if (logs.size() > 0) {
            for (String str : logs) {
                System.out.println(str);
            }
        } else {
            throw new LogException("There are no logs to display.");
        }
    }
}
