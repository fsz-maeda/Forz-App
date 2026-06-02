package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}