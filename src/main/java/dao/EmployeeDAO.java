package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Port;

public class EmployeeDAO {
	String JDBC_URL = Port.JDBC_URL;
	
	public List<Employee> findAll(){
		List<Employee> employeeList = new ArrayList<>();
		
	try {
		Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		Connection conn = DriverManager.getConnection(JDBC_URL);
		
	String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLOYEE_ID";
	
	 PreparedStatement pStmt = conn.prepareStatement(sql);
	 
	ResultSet rs = pStmt.executeQuery();
	
	while(rs.next()) {
		Employee emp = new Employee();
		 emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));

             emp.setName(rs.getString("NAME"));

             emp.setDepartment(rs.getString("DEPARTMENT"));

             emp.setPosition(rs.getString("POSITION"));

             emp.setPhotoPath(rs.getString("PHOTO_PATH"));

             employeeList.add(emp);
         }
	conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	
	}
	return employeeList;
		
	}
	 public List<Employee> search(String keyword){

	        List<Employee> employeeList = new ArrayList<>();

	        try {

	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	            Connection conn =DriverManager.getConnection(JDBC_URL);

	            String sql ="SELECT * FROM EMPLOYEE WHERE NAME LIKE ?";

	            PreparedStatement pStmt =conn.prepareStatement(sql);

	            pStmt.setString(1,"%" + keyword + "%");

	            ResultSet rs =pStmt.executeQuery();

	            while(rs.next()) {

	                Employee emp =new Employee();

	                emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));

	                emp.setName(rs.getString("NAME"));

	                emp.setDepartment(rs.getString("DEPARTMENT"));

	                emp.setPosition(rs.getString("POSITION"));

	                emp.setPhotoPath(rs.getString("PHOTO_PATH"));

	                employeeList.add(emp);
	            }

	            conn.close();

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        return employeeList;
	    }
public Employee findById(int employeeId) {
	Employee emp = null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(JDBC_URL);
		String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=?";
		
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		pStmt.setInt(1, employeeId);
		
		ResultSet rs = pStmt.executeQuery();
		
	if(rs.next()) {
		emp = new Employee();
		emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
		
		emp.setName(rs.getString("NAME"));
		
		emp.setDepartment(rs.getString("DEPARTMENT"));
		
		emp.setPosition(rs.getString("POSITION"));
		
		emp.setPhotoPath(rs.getString("PHOTO_PATH"));
	}
	conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	return emp;
	
}
}
