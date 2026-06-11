package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import model.Port;

public class DailyReportLikeDAO {
	String JDBC_URL = Port.JDBC_URL;
	
//	いいねを押した人とレポ－トIDの保存
	public boolean insertLikeEmployee(int employeeId, int dailyReportId) {
		
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }
	    
	    String sql = "INSERT INTO FORZDAILYREPORTLIKE (employee_id,daily_report_id)"
	    		+ "Values(?,?)";
	    
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
                PreparedStatement pStmt = conn.prepareStatement(sql)) {
        	
        	pStmt.setInt(1, employeeId);
        	pStmt.setInt(2, dailyReportId);
        	
        	int result = pStmt.executeUpdate();
        	
        	return result == 1;
        	
  
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	}
	
	
//	ログインユーザーがいいねを押している日報一覧を取得
	public Set<Integer> findLikedReportIds(int employeeId) {

	    Set<Integer> set = new HashSet<>();

	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }

	    String sql =
	        "SELECT daily_report_id " +
	        "FROM FORZDAILYREPORTLIKE " +
	        "WHERE employee_id = ?";

	    try (Connection conn = DriverManager.getConnection(JDBC_URL);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            set.add(rs.getInt("daily_report_id"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return set;
	}
	
//	いいねを削除
	public boolean deleteLikeEmployee(int employeeId, int dailyReportId) {

	    String sql =
	        "DELETE FROM FORZDAILYREPORTLIKE WHERE employee_id=? AND daily_report_id=?";

	    try (Connection conn = DriverManager.getConnection(JDBC_URL);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setInt(2, dailyReportId);

	        return ps.executeUpdate() == 1;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}