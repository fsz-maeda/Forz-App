package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PaidHolidayService {

    public double calcUsedDays(Date startDate, Date finishDate) {

        LocalDate start = startDate.toLocalDate();
        LocalDate finish = finishDate.toLocalDate();

        if (finish.isBefore(start)) {
            return -1;
        }

        return ChronoUnit.DAYS.between(start, finish) + 1;
    }
}