package dev.gokhana.dataredis.util;

import java.time.LocalDate;

public class TimeUtil {

    public static int getDaysUntilEndOfYear() {
        // get current day count of year
        int currentDayOfYear = LocalDate.now().getDayOfYear();
        // get total days of current year
        int totalDaysOfYear = LocalDate.now().lengthOfYear();
        return totalDaysOfYear - currentDayOfYear;
    }
}
