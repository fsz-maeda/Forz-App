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
import model.Port;

public class EmployeeDAO {
	String JDBC_URL = Port.JDBC_URL;

	public Employee login(String name, String pass) {
		Employee employee = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
			String sql = "SELECT * FROM EMPLOYEE WHERE NAME = ? AND PASS = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String mail = rs.getString("MAIL");
				int positionId = rs.getInt("POSITION_ID");
				int departmentId = rs.getInt("DEPARTMENT_ID");
				String photoPath = rs.getString("PHOTO_PATH");
				Date enter = rs.getDate("ENTER");
				String intro = rs.getString("INTRO");
				int management = rs.getInt("MANAGEMENT");
				employee = new Employee(employeeId, name, pass, mail, positionId, departmentId,
						photoPath, enter, intro, management);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return employee;
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
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PATH");
        		String mail = rs.getString("MAIL");
        		int positionId = rs.getInt("POSITION_ID");
        		int departmentId = rs.getInt("DEPARTMENT_ID");
        		String photoPath = rs.getString("PHOTO_PATH");
        		Date enter = rs.getDate("ENTER");
        		String intro = rs.getString("INTRO");
        		int management = rs.getInt("MANAGEMENT");
        		
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
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PATH");
        		String mail = rs.getString("MAIL");
        		int positionId = rs.getInt("POSITION_ID");
        		int departmentId = rs.getInt("DEPARTMENT_ID");
        		String photoPath = rs.getString("PHOTO_PATH");
        		Date enter = rs.getDate("ENTER");
        		String intro = rs.getString("INTRO");
        		int management = rs.getInt("MANAGEMENT");
        		
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
        		int management = rs.getInt("MANAGEMENT");
        		
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
        		int management = rs.getInt("MANAGEMENT");
        		
        		emp = new Employee(employeeId, name, pass, mail, positionId, departmentId, 
        				photoPath, enter, intro, management);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emp;
	}
}
