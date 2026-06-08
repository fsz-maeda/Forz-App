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

//  日報保存処理
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
    
    
//  日報削除処理
    public boolean dailyReportDelete(int userId, int reportId) {
    	
    	
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

            conn.setAutoCommit(false);

            try {

//              いいね削除
                String deleteLikes =
                    "DELETE FROM FORZDAILYREPORTLIKE WHERE daily_report_id = ?";

                try (PreparedStatement ps = conn.prepareStatement(deleteLikes)) {
                    ps.setInt(1, reportId);
                    ps.executeUpdate();
                }

//              コメント削除
                String deleteComments =
                    "DELETE FROM FORZDAILYREPORTCOMMENT WHERE daily_report_id = ?";

                try (PreparedStatement ps = conn.prepareStatement(deleteComments)) {
                    ps.setInt(1, reportId);
                    ps.executeUpdate();
                }

//              本体削除
                String deleteReport =
                    "DELETE FROM FORZDAILYREPORTS WHERE daily_report_id = ? AND user_id = ?";

                try (PreparedStatement ps = conn.prepareStatement(deleteReport)) {
                    ps.setInt(1, reportId);
                    ps.setInt(2, userId);

                    int result = ps.executeUpdate();

                    conn.commit();
                    return result > 0;
                }

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    
//  日報の取得
    public List<DailyReport> findPagedReports(int offset, int limit) {

        List<DailyReport> list = new ArrayList<>();

        String sql =
            "SELECT " +
            " daily_report.daily_report_id, " +
            " daily_report.user_id, " +
            " daily_report.report_type, " +
            " daily_report.title, " +
            " daily_report.content, " +
            " daily_report.created_at, " +
            " user_table.name AS user_name, " +
            " (SELECT COUNT(*) FROM FORZDAILYREPORTLIKE l " +
            "  WHERE l.daily_report_id = daily_report.daily_report_id) AS like_count " +
            "FROM FORZDAILYREPORTS daily_report " +
            "LEFT JOIN FORZUSERS user_table " +
            " ON daily_report.user_id = user_table.id " +
            "ORDER BY daily_report.created_at DESC " +
            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
//        	ページネーション用
            ps.setInt(1, offset);
            ps.setInt(2, limit);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DailyReport r = new DailyReport();
                    r.setDailyReportId(rs.getInt("daily_report_id"));
                    r.setUserId(rs.getInt("user_id"));
                    r.setReportType(rs.getString("report_type"));
                    r.setTitle(rs.getString("title"));
                    r.setContent(rs.getString("content"));
                    r.setCreatedAt(rs.getTimestamp("created_at"));
                    r.setUserName(rs.getString("user_name"));
                    r.setLikes(rs.getInt("like_count"));

                    list.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    
    public int countAllReports() {

        String sql = "SELECT COUNT(*) FROM FORZDAILYREPORTS";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    
////  コメント取得
//    public List<DailyReportComment> findCommentsByReportId(int reportId) {
//
//        List<DailyReportComment> list = new ArrayList<>();
//
//        String sql =
//            "SELECT c.comment_id, c.comment_text,c.user_id, u.name " +
//            "FROM FORZDAILYREPORTCOMMENT c " +
//            "LEFT JOIN FORZUSERS u ON c.user_id = u.id " +
//            "WHERE c.daily_report_id = ? " +
//            "ORDER BY c.created_at ASC";
//
//        try (Connection conn = DriverManager.getConnection(JDBC_URL);
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, reportId);
//
//            try (ResultSet rs = ps.executeQuery()) {
//
//                while (rs.next()) {
//                    DailyReportComment c = new DailyReportComment();
//                    c.setCommentId(rs.getInt("comment_id"));
//                    c.setCommentText(rs.getString("comment_text"));
//                    c.setUserId(rs.getInt("user_id"));
//                    c.setUserName(rs.getString("name"));
//                    list.add(c);
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//    

    
//    public List<DailyReport> findAllWithComments(Set<Integer> likedSet) {
//
//        List<DailyReport> result = new ArrayList<>();
//
//        String sql =
//        	    "SELECT " +
//        	    " daily_report.daily_report_id, " +
//        	    " daily_report.user_id, " +
//        	    " daily_report.report_type, " +
//        	    " daily_report.title, " +
//        	    " daily_report.content, " +
//        	    " daily_report.created_at, " +
//        	    " user_table.name AS user_name, " +
//
//        	    " (SELECT COUNT(*) FROM FORZDAILYREPORTLIKE l " +
//        	    "   WHERE l.daily_report_id = daily_report.daily_report_id) AS like_count, " +
//
//        	    " comment.comment_id, " +
//        	    " comment.comment_text, " +
//        	    " comment_user.name AS comment_user_name " +
//
//        	    "FROM FORZDAILYREPORTS daily_report " +
//        	    "LEFT JOIN FORZUSERS user_table " +
//        	    "    ON daily_report.user_id = user_table.id " +
//        	    "LEFT JOIN FORZDAILYREPORTCOMMENT comment " +
//        	    "    ON daily_report.daily_report_id = comment.daily_report_id " +
//        	    "LEFT JOIN FORZUSERS comment_user " +
//        	    "    ON comment.user_id = comment_user.id " +
//
//        	    "ORDER BY daily_report.created_at DESC,\r\n"
//        	    + "         comment.created_at ASC";
//
//        try (Connection conn = DriverManager.getConnection(JDBC_URL);
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            Map<Integer, DailyReport> map = new LinkedHashMap<>();
//
//            while (rs.next()) {
//
//                int dailyReportId = rs.getInt("daily_report_id");
//
//                if (!map.containsKey(dailyReportId)) {
//
//                    DailyReport dailyReport = new DailyReport();
//                    dailyReport.setDailyReportId(dailyReportId);
//                    dailyReport.setUserId(rs.getInt("user_id"));
//                    dailyReport.setReportType(rs.getString("report_type"));
//                    dailyReport.setTitle(rs.getString("title"));
//                    dailyReport.setContent(rs.getString("content"));
//                    dailyReport.setCreatedAt(rs.getTimestamp("created_at"));
//
//                    
//                    dailyReport.setUserName(rs.getString("user_name"));
//
//                    dailyReport.setLiked(likedSet.contains(dailyReportId));
//                    
//                    dailyReport.setLikes(rs.getInt("like_count"));
//
//                    dailyReport.setCommentList(new ArrayList<>());
//
//                    map.put(dailyReportId, dailyReport);
//                }
//
//                if (rs.getString("comment_id") != null) {
//
//                    DailyReportComment comment = new DailyReportComment();
//                    comment.setCommentId(rs.getInt("comment_id"));
//                    comment.setCommentText(rs.getString("comment_text"));
//                    comment.setUserName(rs.getString("comment_user_name"));
//
//                    map.get(dailyReportId).getCommentList().add(comment);
//                }
//            }
//
//            result = new ArrayList<>(map.values());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
    

}