package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Port;

public class DepartmentDAO {
	String JDBC_URL = Port.JDBC_URL;

	//部署テーブルをすべて表示
	public List<Department> findAll() {
		List<Department> departmentList = new ArrayList<>();
		Department department = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENT";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				department = new Department(departmentId, departmentName);
				departmentList.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return departmentList;
	}

	//指定した部署IDをもつデータを取得
	public Department findByDepartmentId(int departmentId) {
		Department department = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT DEPARTMENT_NAME FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, departmentId);

			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				String departmentName = rs.getString("DEPARTMENT_NAME");
				department = new Department(departmentId, departmentName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return department;
	}

	//部署テーブルに新規データを登録
	public boolean insertDepartment(String departmentName) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		String sql = "INSERT INTO DEPARTMENT(DEPARTMENT_NAME) VALUES(?)";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setString(1, departmentName);

			int result = pStmt.executeUpdate();
			return result == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//部署テーブルを更新
	public boolean updateDepartment(int departmentId, String departmentName) {

		String sql = "UPDATE DEPARTMENT " +
				"SET DEPARTMENT_NAME = ? " +
				"WHERE DEPARTMENT_ID = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setString(1, departmentName);
			pStmt.setInt(2, departmentId);

			return pStmt.executeUpdate() == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//指定した部署IDをもつデータを削除
	public boolean deleteDepartment(int departmentId) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, departmentId);

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

	public boolean existsDepartmentName(String departmentName) {

		try {
			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}

		String sql = "SELECT COUNT(*) " +
				"FROM DEPARTMENT " +
				"WHERE DEPARTMENT_NAME = ?";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setString(1, departmentName);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean existsById(int departmentId) {

	    String sql =
	        "SELECT 1 " +
	        "FROM DEPARTMENT " +
	        "WHERE DEPARTMENT_ID = ?";

	    try (
	        Connection conn =
	            DriverManager.getConnection(JDBC_URL);

	        PreparedStatement pStmt =
	            conn.prepareStatement(sql)
	    ) {

	        pStmt.setInt(1, departmentId);

	        ResultSet rs = pStmt.executeQuery();

	        return rs.next();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean existsDepartmentNameExceptSelf(
	        int departmentId,
	        String departmentName) {

	    try {
	        Class.forName(
	            "com.microsoft.sqlserver.jdbc.SQLServerDriver");

	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException(
	            "JDBCドライバを読み込めませんでした");
	    }

	    String sql =
	        "SELECT COUNT(*) " +
	        "FROM DEPARTMENT " +
	        "WHERE DEPARTMENT_NAME = ? " +
	        "AND DEPARTMENT_ID <> ?";

	    try (Connection conn =
	             DriverManager.getConnection(JDBC_URL);
	         PreparedStatement pStmt =
	             conn.prepareStatement(sql)) {

	        pStmt.setString(1, departmentName);
	        pStmt.setInt(2, departmentId);

	        ResultSet rs = pStmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
}
