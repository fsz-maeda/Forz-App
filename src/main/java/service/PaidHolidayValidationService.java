package service;

import model.Employee;

public class PaidHolidayValidationService {

	/**
	 * 有給使用可能チェック
	 */
	public boolean canUsePaidHoliday(Employee employee, double usingDays) {

		// nullチェック
		if (employee == null) {
			throw new IllegalArgumentException("employee is null");
		}

		return usingDays <= employee.getRemainPaidHoliday();
	}
}