package co.lodiser.perceo;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addHour(Date date, int hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour); //minus number would decrement the days
        return cal.getTime();
    }

    public static String printTitlePeriodDate(Date before, Date current) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("es", "CO"));
        SimpleDateFormat dayFormat = new SimpleDateFormat("d", new Locale("es", "CO"));

        String dayBefore = dayFormat.format(before);
        String monthBefore = StringUtils.upperCase(monthFormat.format(before));

        String dayCurrent = dayFormat.format(current);
        String monthCurrent = StringUtils.upperCase(monthFormat.format(current));

        if (monthBefore.equals(monthCurrent)) {
            sb.append(dayBefore).append(" al ").append(dayCurrent).append(" de ").append(monthBefore);
        } else {
            sb.append(dayBefore).append(" de ").append(monthBefore).append(" al ").append(dayCurrent).append(" de ").append(monthCurrent);
        }

        return sb.toString();
    }
}