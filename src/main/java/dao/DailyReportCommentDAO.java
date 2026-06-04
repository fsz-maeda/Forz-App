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

    public List<DailyReportComment> findAllByReportId(int dailyReportId) {

        List<DailyReportComment> commentList = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql =
            "SELECT " +
            " comment.comment_id, " +
            " comment.daily_report_id, " +
            " comment.user_id, " +
            " user.name AS user_name, " +
            " comment.comment_text, " +
            " comment.created_at " +
            "FROM FORZDAILYREPORTCOMMENT comment " +
            "JOIN FORZUSERS user ON comment.user_id = user.id " +
            "WHERE comment.daily_report_id = ? " +
            "ORDER BY comment.created_at ASC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dailyReportId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DailyReportComment comment = new DailyReportComment();

                    comment.setCommentId(rs.getInt("comment_id"));
                    comment.setDailyReportId(rs.getInt("daily_report_id"));
                    comment.setUserId(rs.getInt("user_id"));
                    comment.setUserName(rs.getString("user_name"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setCreatedAt(rs.getTimestamp("created_at"));

                    commentList.add(comment);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commentList;
    }
    
    
    public boolean insertComment(int userId, int reportId, String comment) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql =
            "INSERT INTO FORZDAILYREPORTCOMMENT (user_id, daily_report_id, comment_text) " +
            "VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, userId);
            pStmt.setInt(2, reportId);
            pStmt.setString(3, comment);

            return pStmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}