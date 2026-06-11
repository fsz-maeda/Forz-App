package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Port;

public class LikeDAO {
	String JDBC_URL = Port.JDBC_URL;

	public boolean exists(int employeeId, int eventId) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT COUNT(*) " +
					"FROM FORZEVENTSLIKE " +
					"WHERE employee_id = ? AND event_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);
			pStmt.setInt(2, eventId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean insert(int employeeId, int eventId) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "INSERT INTO FORZEVENTSLIKE " +
					"(employee_id,event_id) " +
					"VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);
			pStmt.setInt(2, eventId);

			return pStmt.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public int countByEventId(int eventId) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT COUNT(*) " +
					"FROM FORZEVENTSLIKE " +
					"WHERE event_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, eventId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean delete(int employeeId, int eventId) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "DELETE FROM FORZEVENTSLIKE " +
					"WHERE employee_id = ? " +
					"AND event_id = ?";
    
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);
			pStmt.setInt(2, eventId);
           
			return pStmt.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}