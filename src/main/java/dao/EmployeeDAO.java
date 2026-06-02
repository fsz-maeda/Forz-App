package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.PortNo;

public class EmployeeDAO {
	String JDBC_URL = PortNo.JDBC_URL;
	
	public List<Employee> findAll(){
		List<Employee> employeeList = new ArrayList<>();
		
	try {
		Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(JDBC_URL);
	String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLLOYEE_ID";
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

}
