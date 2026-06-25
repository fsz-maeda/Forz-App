package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PaidHoliday;
import model.Port;

public class PaidHolidayDAO {

	private final String JDBC_URL = Port.JDBC_URL;

	// =========================
	// 全件取得
	// =========================
	public List<PaidHoliday> findAll() {

		List<PaidHoliday> list = new ArrayList<>();

		String sql = "SELECT PAIDHOLIDAY_ID, EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE, HOLIDAY_TYPE, STATUS "
				+ "FROM PAIDHOLIDAY";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// =========================
	// 1件取得
	// =========================
	public PaidHoliday findByPaidHolidayId(int paidHolidayId) {

		String sql = "SELECT PAIDHOLIDAY_ID, EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE, HOLIDAY_TYPE, STATUS "
				+ "FROM PAIDHOLIDAY WHERE PAIDHOLIDAY_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, paidHolidayId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return map(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// =========================
	// 従業員別取得
	// =========================
	public List<PaidHoliday> findByEmployeeId(int employeeId) {

		List<PaidHoliday> list = new ArrayList<>();

		String sql = "SELECT PAIDHOLIDAY_ID, EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE, HOLIDAY_TYPE, STATUS "
				+ "FROM PAIDHOLIDAY WHERE EMPLOYEE_ID = ? ORDER BY STARTDATE DESC";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(map(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// =========================
	// 新規登録
	// =========================
	public boolean insertPaidHoliday(int employeeId, double usedDays,
			Date startDate, Date finishDate, String holidayType) {

		String sql = "INSERT INTO PAIDHOLIDAY "
				+ "(EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE, HOLIDAY_TYPE) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);
			ps.setDouble(2, usedDays);
			ps.setDate(3, startDate);
			ps.setDate(4, finishDate);
			ps.setString(5, holidayType);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// =========================
	// ステータス更新（承認など）
	// =========================
	public boolean updateStatus(int paidHolidayId, String status) {

		String sql = "UPDATE PAIDHOLIDAY SET STATUS = ? WHERE PAIDHOLIDAY_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, paidHolidayId);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// =========================
	// 使用日数合計
	// =========================
	public double sumUsedDays(int employeeId) {

		String sql = "SELECT SUM(USED_DAYS) AS TOTAL FROM PAIDHOLIDAY WHERE EMPLOYEE_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getDouble("TOTAL");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	// =========================
	// 更新
	// =========================
	public boolean updatePaidHoliday(int paidHolidayId,
			double usedDays,
			Date startDate,
			Date finishDate,
			String holidayType) {

		String sql = "UPDATE PAIDHOLIDAY "
				+ "SET USED_DAYS = ?, STARTDATE = ?, FINISHDATE = ?, HOLIDAY_TYPE = ? "
				+ "WHERE PAIDHOLIDAY_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setDouble(1, usedDays);
			ps.setDate(2, startDate);
			ps.setDate(3, finishDate);
			ps.setString(4, holidayType);
			ps.setInt(5, paidHolidayId);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// =========================
	// 削除
	// =========================
	public boolean deletePaidHoliday(int paidHolidayId) {

		String sql = "DELETE FROM PAIDHOLIDAY WHERE PAIDHOLIDAY_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, paidHolidayId);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// =========================
	// ResultSet → Entity変換
	// =========================
	private PaidHoliday map(ResultSet rs) throws SQLException {

		return new PaidHoliday(
				rs.getInt("PAIDHOLIDAY_ID"),
				rs.getInt("EMPLOYEE_ID"),
				rs.getDouble("USED_DAYS"),
				rs.getDate("STARTDATE"),
				rs.getDate("FINISHDATE"),
				rs.getString("HOLIDAY_TYPE"),
				rs.getString("STATUS")
		);
	}
}