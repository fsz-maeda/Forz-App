package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Port;

public class SystemSettingDAO {
	
	private final String JDBC_URL = Port.JDBC_URL;

	// 共通：接続
	private Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(JDBC_URL);
	}


//	締め日の取得
    public int getCloseDay() {

        String sql = """
            SELECT setting_value
            FROM SYSTEM_SETTING
            WHERE setting_key = 'CLOSE_DAY'
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return Integer.parseInt(rs.getString("setting_value"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 25; // fallback
    }
    
    
//  締め日の更新
    public void updateCloseDay(int closeDay) {

        String updateSql = """
            UPDATE SYSTEM_SETTING
            SET setting_value = ?
            WHERE setting_key = 'CLOSE_DAY'
        """;

        String insertSql = """
            INSERT INTO SYSTEM_SETTING (setting_key, setting_value)
            VALUES ('CLOSE_DAY', ?)
        """;

        try (Connection conn = getConnection()) {

            conn.setAutoCommit(false);

            try (
                PreparedStatement ps = conn.prepareStatement(updateSql)
            ) {
                ps.setInt(1, closeDay);

                int updated = ps.executeUpdate();

                if (updated == 0) {
                    try (PreparedStatement ps2 = conn.prepareStatement(insertSql)) {
                        ps2.setInt(1, closeDay);
                        ps2.executeUpdate();
                    }
                }

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}