package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import model.Employee;

public class PaidHolidayService {
	public double calcUsedDays(Date startDate, Date finishDate, String holidayType) {
		LocalDate start = startDate.toLocalDate();
		LocalDate finish = finishDate.toLocalDate();

		if (finish.isBefore(start)) {
			return -1;
		}
		if ("午前休".equals(holidayType) || "午後休".equals(holidayType)) {
			if (!start.equals(finish)) {
				return -1;
			}
			return 0.5;
		}

		return ChronoUnit.DAYS.between(start, finish) + 1;
	}
	
	public boolean checkUsedDays(int employeeId, double usingDays, Employee employee) {
		if (usingDays <= employee.getRemainPaidHoliday()) {
			return true;
		} else {
			return false;
		}
	}
}
