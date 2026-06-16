package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DailyReportComment;
import model.Port;

public class DailyReportCommentDAO {

    String JDBC_URL = Port.JDBC_URL;
    
    
    public List<DailyReportComment> findByReportId(int reportId) {

        List<DailyReportComment> list = new ArrayList<>();

        String sql =
        	    "SELECT " +
        	    " comment.comment_id, " +
        	    " comment.comment_text, " +
        	    " comment.employee_id, " +
        	    " employee_table.name " +
        	    "FROM FORZDAILYREPORTCOMMENT comment " +
        	    "LEFT JOIN EMPLOYEE employee_table " +
        	    "    ON comment.employee_id = employee_table.id " +
        	    "WHERE comment.daily_report_id = ? " +
        	    "ORDER BY comment.created_at ASC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reportId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DailyReportComment c = new DailyReportComment();
                    c.setCommentId(rs.getInt("comment_id"));
                    c.setCommentText(rs.getString("comment_text"));
                    c.setEmployeeId(rs.getInt("employee_id"));
                    c.setEmployeeName(rs.getString("name"));

                    list.add(c);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public boolean insertComment(int employeeId, int reportId, String comment) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql =
            "INSERT INTO FORZDAILYREPORTCOMMENT (employee_id, daily_report_id, comment_text) " +
            "VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, employeeId);
            pStmt.setInt(2, reportId);
            pStmt.setString(3, comment);

            return pStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteComment(int commentId, int employeeId) {

        String sql =
            "DELETE FROM FORZDAILYREPORTCOMMENT " +
            "WHERE comment_id = ? AND employee_id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.setInt(2, employeeId);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }  
}