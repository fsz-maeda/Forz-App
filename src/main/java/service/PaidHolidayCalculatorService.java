package service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import model.HolidayType;

public class PaidHolidayCalculatorService {

	/**
	 * 有給使用日数を計算する
	 */
	public double calcUsedDays(Date startDate, Date finishDate, HolidayType type) {

		// =========================
		// nullチェック
		// =========================
		if (startDate == null) {
			throw new IllegalArgumentException("startDate is null");
		}

		if (finishDate == null) {
			throw new IllegalArgumentException("finishDate is null");
		}

		if (type == null) {
			throw new IllegalArgumentException("holidayType is null");
		}

		// =========================
		// Date → LocalDate
		// =========================
		LocalDate start = startDate.toLocalDate();
		LocalDate finish = finishDate.toLocalDate();

		// =========================
		// 日付チェック
		// =========================
		if (finish.isBefore(start)) {
			throw new IllegalArgumentException("finishDate is before startDate");
		}

		// =========================
		// 半休処理
		// =========================
		if (type == HolidayType.AM_HALF || type == HolidayType.PM_HALF) {

			if (!start.equals(finish)) {
				throw new IllegalArgumentException("half day must be same date");
			}

			return 0.5;
		}

		// =========================
		// 通常休暇
		// =========================
		return ChronoUnit.DAYS.between(start, finish) + 1;
	}
}