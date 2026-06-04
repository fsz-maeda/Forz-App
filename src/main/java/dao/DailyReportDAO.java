package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DailyReport;
import model.Port;

public class DailyReportDAO {
    String JDBC_URL = Port.JDBC_URL;

    public boolean insertDaylyReport(int userId, String reportType, String title, String content) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql =
                "INSERT INTO FORZDAILYREPORTS (user_id, report_type, title, content) "
              + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, userId);
            pStmt.setString(2, reportType);
            pStmt.setString(3, title);
            pStmt.setString(4, content);

            int result = pStmt.executeUpdate();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<DailyReport> findAll(){
    	
    	List<DailyReport> list = new ArrayList<>();
    	
    	
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql =
        	    "SELECT " +
        	    " r.daily_report_id, " +
        	    " r.user_id, " +
        	    " u.name AS user_name, " +
        	    " r.report_type, " +
        	    " r.title, " +
        	    " r.content, " +
        	    " r.created_at, " +
        	    " COUNT(l.like_id) AS likes " +
        	    "FROM FORZDAILYREPORTS r " +
        	    "JOIN FORZUSERS u ON r.user_id = u.ID " +
        	    "LEFT JOIN FORZDAILYREPORTLIKE l " +
        	    " ON l.daily_report_id = r.daily_report_id " +
        	    "GROUP BY " +
        	    " r.daily_report_id, " +
        	    " r.user_id, " +
        	    " u.name, " +
        	    " r.report_type, " +
        	    " r.title, " +
        	    " r.content, " +
        	    " r.created_at " +
        	    "ORDER BY r.created_at DESC";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
                PreparedStatement pStmt = conn.prepareStatement(sql);
        		ResultSet rs = pStmt.executeQuery()){
        	
        	while(rs.next()) {
        		DailyReport dr = new DailyReport();
        		
        		dr.setDailyReportId(rs.getInt("daily_report_id"));
        		dr.setUserId(rs.getInt("user_id"));
        		dr.setReportType(rs.getString("report_type"));
        		dr.setTitle(rs.getString("title"));
        		dr.setContent(rs.getString("content"));
        		dr.setCreatedAt(rs.getTimestamp("created_at"));
        		dr.setUserName(rs.getString("user_name"));
        		dr.setLikes(rs.getInt("likes"));
        		
                list.add(dr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}