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
				holiday = new PaidHoliday(paidHolidayId, employeeId, usedDays, startDate, finishDate);
				holidayList.add(holiday);
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return holidayList;
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
				holiday = new PaidHoliday(paidHolidayId, employeeId, usedDays, startDate, finishDate);
				holidayList.add(holiday);
			}
		}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return holidayList;
	}
	
	public boolean insertPaidHoliday(int employeeId, double usedDays, Date startDate, Date finishDate) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "INSERT INTO PAIDHOLIDAY "
                    + "(EMPLOYEE_ID, USED_DAYS, STARTDATE, FINISHDATE) "
                    + "VALUES (?, ?, ?, ?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);
			pStmt.setDouble(2, usedDays);
			pStmt.setDate(3, startDate);
			pStmt.setDate(4, finishDate);
			
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
}
