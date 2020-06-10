package sg.gov.tech.crmspoc.datasource;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Util {

    public final static String yearCycleToISODate(int year, String cycle) {
        int month = Calendar.DECEMBER;
        int day = 31;
        if (cycle != null) {
            // Determine month, day from cycle
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
        return sdf.format(calendar.getTime());
    }
}
