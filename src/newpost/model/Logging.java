package newpost.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Vladislav on 10.07.2016.
 */
public class Logging {

    private static List<String> logs;
    private static DateFormat dateFormat;
    private static Calendar cal;
    private static String dateTime;

    {
        logs = new ArrayList<>();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        cal = Calendar.getInstance();
    }

    public static void logEvent(String event) {
        dateTime = dateFormat.format(cal.getTime());
        logs.add(dateTime + " " + event);
    }

    public static void showAllLogs(){
        for (String str : logs){
            System.out.println(str);
        }
    }
}
