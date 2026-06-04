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
	
	public boolean insertLikeUser(int userId, int dailyReportId) {
		
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }
	    
	    String sql = "INSERT INTO FORZDAILYREPORTLIKE (user_id,daily_report_id)"
	    		+ "Values(?,?)";
	    
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
                PreparedStatement pStmt = conn.prepareStatement(sql)) {
        	
        	pStmt.setInt(1, userId);
        	pStmt.setInt(2, dailyReportId);
        	
        	int result = pStmt.executeUpdate();
        	
        	return result == 1;
        	
  
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	}
	
	public boolean isLiked(int userId, int reportId) {
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }
		
		
	    String sql = "SELECT COUNT(*) FROM FORZDAILYREPORTLIKE WHERE user_id=? AND daily_report_id=?";

	    try (Connection conn = DriverManager.getConnection(JDBC_URL);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, userId);
	        ps.setInt(2, reportId);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public Set<Integer> findLikedReportIds(int userId) {

	    Set<Integer> set = new HashSet<>();

	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }

	    String sql =
	        "SELECT daily_report_id " +
	        "FROM FORZDAILYREPORTLIKE " +
	        "WHERE user_id = ?";

	    try (Connection conn = DriverManager.getConnection(JDBC_URL);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            set.add(rs.getInt("daily_report_id"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return set;
	}
}