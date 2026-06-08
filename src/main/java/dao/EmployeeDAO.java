package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.EmployeePosition;
import model.Port;
import util.PasswordUtil;

public class EmployeeDAO {
	String JDBC_URL = Port.JDBC_URL;

	public boolean login(String name, String pass) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String hashedPass = PasswordUtil.hashPassword(pass);
        	
        	String sql = "SELECT * FROM EMPLOYEE WHERE NAME = ? AND PASS = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, hashedPass);
            
            ResultSet rs = pStmt.executeQuery();

            return rs.next();
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public List<Employee> findAll() {
		List<Employee> employeeList = new ArrayList<>();
		Employee emp = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(JDBC_URL);

			String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLOYEE_ID";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				boolean management = rs.getBoolean("MANAGEMENT");

				emp = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
				employeeList.add(emp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return employeeList;

	}

	public List<Employee> search(String keyword) {

		List<Employee> employeeList = new ArrayList<>();
		Employee emp = null;

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(JDBC_URL);

			String sql = "SELECT * FROM EMPLOYEE WHERE NAME LIKE ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, "%" + keyword + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int employeeId = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PATH");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				boolean management = rs.getBoolean("MANAGEMENT");

				emp = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
				employeeList.add(emp);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	public Employee findById(int employeeId) {
		List<Employee> employeeList = new ArrayList<>();
		Employee emp = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(JDBC_URL);
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				boolean management = rs.getBoolean("MANAGEMENT");

				emp = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
				employeeList.add(emp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;

	}

	public Employee findByUserId(int employeeId) {

		Employee emp = null;

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(JDBC_URL);

			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, employeeId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				boolean management = rs.getBoolean("MANAGEMENT");

				emp = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}

	public boolean deleteEmployee(int employeeId) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "DELETE FROM EMPLOYEE WHERE = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, employeeId);

			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public List<EmployeePosition> findPositionName() {
		EmployeePosition userPosition = null;
		List<EmployeePosition> userPositionList = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT FORZUSERS.ID, "
					+ "FORZUSERS.NAME, "
					+ "FORZUSERS.PASS, "
					+ "FORZUSERS.MAIL, "
					+ "POSITION.POSITION_NAME "
					+ "FROM FORZUSERS "
					+ "JOIN POSITION ON FORZUSERS.position_id = POSITION.POSITION_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String positionName = rs.getString("POSITION_NAME");
				userPosition = new EmployeePosition(id, name, pass, mail, positionName);
				userPositionList.add(userPosition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return userPositionList;
	}

	public Employee findByNameAndPass(String name, String pass) {
		Employee employee = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String hashedPass = PasswordUtil.hashPassword(pass);

			String sql = "SELECT * FROM EMPLOYEE WHERE NAME = ? AND PASS = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, hashedPass);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int employeeId = rs.getInt("ID");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				boolean management = rs.getBoolean("MANAGEMENT");

				employee = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return employee;
	}

	public boolean updateEmployeePosition(String positionName, int employeeId) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "UPDATE EMPLOYEE "
					+ "SET POSITION_ID = POSITION.POSITION_ID "
					+ "FROM EMPLOYEE "
					+ "JOIN POSITION ON POSITION.POSITION_NAME = ? "
					+ "WHERE EMPLOYEE.ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, positionName);
			pStmt.setInt(2, employeeId);

			int result = pStmt.executeUpdate();
			return result == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerEmployee(String name, String pass, String mail) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			// パスワードをハッシュ化
			String hashedPass = PasswordUtil.hashPassword(pass);

			String sql = "INSERT INTO EMPLOYEE(NAME, PASS, MAIL) "
					+ "VALUES (?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, hashedPass);
			pStmt.setString(3, mail);

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
}
