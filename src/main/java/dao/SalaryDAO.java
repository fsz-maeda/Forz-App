package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Port;
import model.Salary;

public class SalaryDAO {
	String JDBC_URL = Port.JDBC_URL;
    
    public List<Salary> findAll(){
    	List<Salary> salaryList = new ArrayList<>();
    	Salary salary = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT SALARY_ID, USER_ID, AMOUNT, SALARY_MONTH FROM SALARY";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
       	 
       	 	ResultSet rs = pStmt.executeQuery();
       	 
       	 	while(rs.next()) {
       	 		int salaryId = rs.getInt("SALARY_ID");
       	 		int userId = rs.getInt("USER_ID");
       	 		int amount = rs.getInt("AMOUNT");
       	 		int month = rs.getInt("SALARY_MONTH");
       	 		salary = new Salary(salaryId, userId, amount, month);
       	 		salaryList.add(salary);
       	 	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return salaryList;
    }
    
    public Salary findBySalaryId(int salaryId) {
    	Salary salary = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT SALARY_ID, USER_ID, AMOUNT, SALARY_MONTH FROM SALARY WHERE SALARY_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, salaryId);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) {
        		int userId = rs.getInt("USER_ID");
        		int amount = rs.getInt("AMOUNT");
        		int month = rs.getInt("SALARY_MONTH");
        		salary = new Salary(salaryId, userId, amount, month);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return salary;
    }
    
    public boolean insertSalary(int userId, int amount, int month) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "INSERT INTO SALARY(USER_ID, AMOUNT, SALARY_MONTH) "
        			+ "VALUES(?, ?, ?)";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, userId);
            pStmt.setInt(2, amount);
            pStmt.setInt(3, month);
            
            int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean updateSalary(Salary salary) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "UPDATE SALARY SET USER_ID = ?, AMOUNT = ?, SALARY_MONTH = ? WHERE SALARY_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, salary.getUserId());
        	pStmt.setInt(2, salary.getAmount());
        	pStmt.setInt(3, salary.getMonth());
        	pStmt.setInt(4, salary.getSalaryId());
        	
        	int result = pStmt.executeUpdate();
			
			if(result != 1) {
				return false;
			}
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean deleteSalary(int salaryId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "DELETE FROM SALARY WHERE SALARY_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, salaryId);
        	
        	int result = pStmt.executeUpdate();
        	
        	if(result != 1) {
        		return false;
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
