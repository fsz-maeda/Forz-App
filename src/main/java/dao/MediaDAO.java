package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Media;
import model.MediaByEmployeeName;
import model.Port;

public class MediaDAO {

	String JDBC_URL = Port.JDBC_URL;

	public List<MediaByEmployeeName> findAll() {
		List<MediaByEmployeeName> mediaList = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT FORZMEDIA.ID, "
					+ "EMPLOYEE.NAME, "
					+ "MEDIA_TYPE, "
					+ "TITLE, "
					+ "CONTENT,created_at "
					+ "FROM FORZMEDIA "
					+ "JOIN EMPLOYEE ON FORZMEDIA.EMPLOYEE_ID = EMPLOYEE.ID "
					+ "ORDER BY created_at DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String employeeName = rs.getString("NAME");
				String mediaType = rs.getString("media_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String created_at = rs.getString("created_at");
				MediaByEmployeeName media = new MediaByEmployeeName(id, employeeName, mediaType, title, content,
						created_at);
				mediaList.add(media);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mediaList;
	}

	public List<Media> findByEmployeeId(int employeeId) {
		List<Media> mediaList = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM FORZMEDIA WHERE EMPLOYEE_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String mediaType = rs.getString("media_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String created_at = rs.getString("created_at");
				Media media = new Media(id, employeeId, mediaType, title, content, created_at);
				mediaList.add(media);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mediaList;
	}

	public List<Media> findAll(int departmentId) {
		List<Media> mediaList = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT ID, EMPLOYEE_ID, MEDIA_TYPE, TITLE, CONTENT, created_at "
					+ "FROM FORZMEDIA "
					+ "WHERE DEPARTMENT_ID = ? "
					+ "ORDER BY created_at DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, departmentId);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				int userId = rs.getInt("EMPLOYEE_ID");
				String mediaType = rs.getString("MEDIA_TYPE");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String created_at = rs.getString("created_at");
				Media media = new Media(id, userId, mediaType, title, content, created_at);
				mediaList.add(media);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mediaList;
	}

	public boolean insert(Media media, int departmentId, int employeeId) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "INSERT INTO FORZMEDIA "
					    + "(EMPLOYEE_ID, MEDIA_TYPE, TITLE, CONTENT, DEPARTMENT_ID) "
					    + "VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, employeeId);
			ps.setString(2, media.getMediaType());
			ps.setString(3, media.getTitle());
			ps.setString(4, media.getContent());
			ps.setInt(5, departmentId);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {
			throw new RuntimeException("MEDIA INSERT失敗", e);
		}
	}

	public Media articleFind(int id) {
		Media media = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT ID,CONTENT,TITLE,EMPLOYEE_ID FROM FORZMEDIA WHERE ID = ?";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String content = rs.getString("CONTENT");
				String title = rs.getString("TITLE");
				int employeeId = rs.getInt("EMPLOYEE_ID");

				media = new Media(id, content, title, employeeId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return media;
	}

	public boolean mediaUpdate(Media media) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "UPDATE FORZMEDIA SET TITLE = ?, CONTENT = ? WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, media.getTitle());
			pStmt.setString(2, media.getContent());
			pStmt.setInt(3, media.getId());

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean mediaDelete(int id) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "DELETE FROM FORZMEDIA WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, id);

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Media> findByCategory(Employee loginUser, String searchType) {
		List<Media> mediaList = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			// 部署IDに加えて、MEDIA_TYPE も条件（WHERE）に指定する
			String sql =
					"SELECT ID, MEDIA_TYPE, TITLE, CONTENT, created_at, EMPLOYEE_ID " +
					"FROM FORZMEDIA " +
					"WHERE DEPARTMENT_ID = ? AND MEDIA_TYPE = ? " +
					"ORDER BY created_at DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, loginUser.getDepartment());
			pStmt.setString(2, searchType); // 👈 絞り込みたいタイプをセット

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String mediaType = rs.getString("media_type");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String created_at = rs.getString("created_at");
				int employeeId = rs.getInt("EMPLOYEE_ID");

				Media media = new Media(id, mediaType, title, content, created_at, employeeId);
				mediaList.add(media);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mediaList;
	}

	public String findName(int u) {
		String name = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT NAME FROM EMPLOYEE WHERE ID = ? ";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, u);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("NAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return name;

	}

}
