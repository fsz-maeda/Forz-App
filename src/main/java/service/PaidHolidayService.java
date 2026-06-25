package service;

import java.sql.Date;
import java.util.List;

import dao.EmployeeDAO;
import dao.PaidHolidayDAO;
import model.Employee;
import model.PaidHoliday;

public class PaidHolidayService {

	private final PaidHolidayDAO paidHolidayDAO = new PaidHolidayDAO();
	private final EmployeeDAO employeeDAO = new EmployeeDAO();

	// =========================
	// 一覧（全件）
	// =========================
	public List<PaidHoliday> getAllPaidHoliday() {
		return paidHolidayDAO.findAll();
	}

	// =========================
	// 一覧（従業員別）
	// =========================
	public List<PaidHoliday> getByEmployeeId(int employeeId) {
		return paidHolidayDAO.findByEmployeeId(employeeId);
	}

	// =========================
	// 1件取得
	// =========================
	public PaidHoliday getById(int paidHolidayId) {
		return paidHolidayDAO.findByPaidHolidayId(paidHolidayId);
	}

	// =========================
	// 申請処理
	// =========================
	public String applyPaidHoliday(int employeeId, Date startDate, Date finishDate, String type) {

		double usedDays = calcUsedDays(startDate, finishDate, type);

		if (usedDays == -1) {
			return "入力エラー";
		}

		Employee employee = employeeDAO.findByUserId(employeeId);
		if (employee == null) {
			return "ユーザー不正";
		}

		if (usedDays > employee.getRemainPaidHoliday()) {
			return "有給不足";
		}

		boolean result = paidHolidayDAO.insertPaidHoliday(
				employeeId, usedDays, startDate, finishDate, type
		);

		return result ? "申請完了" : "申請失敗";
	}

	// =========================
	// 承認処理
	// =========================
	public boolean approvePaidHoliday(int paidHolidayId, String status) {

		if (status == null) return false;

		boolean result = paidHolidayDAO.updateStatus(paidHolidayId, status);

		if (!result) return false;

		if ("承認".equals(status)) {

			PaidHoliday holiday = paidHolidayDAO.findByPaidHolidayId(paidHolidayId);

			if (holiday == null) return false;

			if (!"承認済み".equals(holiday.getStatus())) {

				employeeDAO.decreaseRemainPaidHoliday(
						holiday.getEmployeeId(),
						holiday.getUsedDays()
				);
			}
		}

		return true;
	}

	// =========================
	// 日数計算
	// =========================
	public double calcUsedDays(Date start, Date end, String type) {

		if (start == null || end == null) {
			return -1;
		}

		if (end.before(start)) {
			return -1;
		}

		// 半休
		if ("午前休".equals(type) || "午後休".equals(type)) {
			if (!start.equals(end)) {
				return -1;
			}
			return 0.5;
		}

		long diff = (end.getTime() - start.getTime())
				/ (1000 * 60 * 60 * 24);

		return diff + 1;
	}

	// =========================
	// 残日数チェック
	// =========================
	public boolean checkUsedDays(Employee employee, double usedDays) {

		if (employee == null) {
			return false;
		}

		return usedDays <= employee.getRemainPaidHoliday();
	}

	// =========================
	// 更新処理
	// =========================
	public String updatePaidHoliday(
			int paidHolidayId,
			int employeeId,
			Date startDate,
			Date finishDate,
			String type) {

		double newUsedDays = calcUsedDays(startDate, finishDate, type);

		if (newUsedDays == -1) {
			return "入力内容に誤りがあります";
		}

		PaidHoliday old = paidHolidayDAO.findByPaidHolidayId(paidHolidayId);

		if (old == null) {
			return "対象データが存在しません";
		}

		double diff = newUsedDays - old.getUsedDays();

		Employee employee = employeeDAO.findByUserId(employeeId);

		if (diff > 0 && !checkUsedDays(employee, diff)) {
			return "有給日数が超過しています";
		}

		boolean ok1 = paidHolidayDAO.updatePaidHoliday(
				paidHolidayId,
				newUsedDays,
				startDate,
				finishDate,
				type
		);

		boolean ok2 = true;

		if (diff > 0) {
			ok2 = employeeDAO.decreaseRemainPaidHoliday(employeeId, diff);
		}

		return (ok1 && ok2) ? "更新完了" : "更新失敗";
	}
}