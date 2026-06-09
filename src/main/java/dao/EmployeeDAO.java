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

			String sql = "SELECT * FROM EMPLOYEE ORDER BY ID";

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

	public Employee findById(int employeeId) {
		List<Employee> employeeList = new ArrayList<>();
		Employee emp = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(JDBC_URL);
			String sql = "SELECT * FROM EMPLOYEE WHERE ID=?";

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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM EMPLOYEE WHERE ID = ?";

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
			String sql = "DELETE FROM EMPLOYEE WHERE ID = ?";

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
		EmployeePosition emlployeePosition = null;
		List<EmployeePosition> PositionList = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT EMPLOYEE.ID, "
					+ "EMPLOYEE.NAME, EMPLOYEE.PASS, "
					+ "EMPLOYEE.MAIL, POSITION.POSITION_NAME, "
					+ "DEPARTMENT.DEPARTMENT_NAME, "
					+ "EMPLOYEE.ENTER, "
					+ "EMPLOYEE.MANAGEMENT "
					+ "FROM EMPLOYEE "
					+ "JOIN POSITION ON EMPLOYEE.POSITION_ID = POSITION.POSITION_ID "
					+ "JOIN DEPARTMENT ON EMPLOYEE.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String positionName = rs.getString("POSITION_NAME");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				String enter = rs.getString("ENTER");
				boolean management = rs.getBoolean("MANAGEMENT");
				emlployeePosition = new EmployeePosition(id, name, pass, mail, positionName,
						departmentName, enter, management);
				PositionList.add(emlployeePosition);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return PositionList;
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

	public boolean updateEmployee(int employeeId, int positionId, 
			int departmentId, String enter, boolean management) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "UPDATE EMPLOYEE "
					+ "SET POSITION_ID = ?, "
					+ "DEPARTMENT_ID = ?, "
					+ "ENTER = ?, "
					+ "MANAGEMENT = ?"
					+ "FROM EMPLOYEE "
					+ "WHERE EMPLOYEE.ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, positionId);
			pStmt.setInt(2, departmentId);
			pStmt.setString(3, enter);
			pStmt.setBoolean(4, management);
			pStmt.setInt(5, employeeId);

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
