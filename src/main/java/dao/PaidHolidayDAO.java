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
	String JDBC_URL = Port.JDBC_URL;

	public List<PaidHoliday> findAll() {
		List<PaidHoliday> holidayList = new ArrayList<>();
		PaidHoliday holiday = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM PAIDHOLIDAY";
			
			PreparedStatement pStmt = conn.prepareCall(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int paidHolidayId = rs.getInt("PAIDHOLIDAY_ID");
				int employeeId = rs.getInt("EMPLOYEE_ID");
				double usedDays = rs.getDouble("USED_DAYS");
				Date startDate = rs.getDate("STARTDATE");
				Date finishDate = rs.getDate("FINISHDATE");
				String holidayType = rs.getString("HOLIDAY_TYPE");
				String status = rs.getString("STATUS");
				holiday = new PaidHoliday(paidHolidayId, employeeId, usedDays, startDate, finishDate, holidayType, status);
				holidayList.add(holiday);
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return holidayList;
	}
	
	public PaidHoliday findByPaidHolidayId(int paidHolidayId) {
		PaidHoliday holiday = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM PAIDHOLIDAY WHERE PAIDHOLIDAY_ID = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, paidHolidayId);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				double usedDays = rs.getDouble("USED_DAYS");
				Date startDate = rs.getDate("STARTDATE");
				Date finishDate = rs.getDate("FINISHDATE");
				String holidayType = rs.getString("HOLIDAY_TYPE");
				String status = rs.getString("STATUS");
				holiday = new PaidHoliday(paidHolidayId, employeeId, usedDays, startDate, finishDate, holidayType, status);
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return holiday;
	}
	
	public List<PaidHoliday> findByEmployeeId(int employeeId){
		List<PaidHoliday> holidayList = new ArrayList<>();
		PaidHoliday holiday = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM PAIDHOLIDAY WHERE EMPLOYEE_ID = ? ORDER BY STARTDATE DESC";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int paidHolidayId = rs.getInt("PAIDHOLIDAY_ID");
				double usedDays = rs.getDouble("USED_DAYS");
				Date startDate = rs.getDate("STARTDATE");
				Date finishDate = rs.getDate("FINISHDATE");
				String holidayType = rs.getString("HOLIDAY_TYPE");
				String status = rs.getString("STATUS");
				holiday = new PaidHoliday(paidHolidayId, employeeId, usedDays, startDate, finishDate, holidayType, status);
				holidayList.add(holiday);
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return holidayList;
	}
	
	public boolean insertPaidHoliday(int employeeId, double usedDays, Date startDate, Date finishDate, String holidayType) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "INSERT INTO PAIDHOLIDAY "
                    + "(EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE, HOLIDAY_TYPE) "
                    + "VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			pStmt.setDouble(2, usedDays);
			pStmt.setDate(3, startDate);
			pStmt.setDate(4, finishDate);
			pStmt.setString(5, holidayType);
			
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
	}
	
	public boolean approvePaidHoliday(int paidHolidayId, String status) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "UPDATE PAIDHOLIDAY SET STATUS = ? WHERE PAIDHOLIDAY_ID = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, status);
			pStmt.setInt(2, paidHolidayId);
			
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				return false;
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
	}
	
	public double checkUsedDays(int employeeId) {
		double usedDays = 0;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT SUM(USED_DAYS) AS USEDDAYS FROM PAIDHOLIDAY WHERE EMPLOYEE_ID = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				usedDays = rs.getDouble("USEDDAYS");
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        
        return usedDays;
	}
}
