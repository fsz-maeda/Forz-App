package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Expenses;
import model.Port;

public class ExpensesDAO {
	String JDBC_URL = Port.JDBC_URL;
    
    public List<Expenses> findAll(){
    	List<Expenses> expensesList = new ArrayList<>();
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM EXPENSES";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	while(rs.next()) {
        		int expensesId = rs.getInt("EXPENSES_ID");
        		int userId = rs.getInt("USER_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, userId, amount, detail, approval);
        		expensesList.add(expenses);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expensesList;
    }
    
    public Expenses findByExpensesId(int expensesId) {
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM EXPENSES WHERE EXPENSES_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, expensesId);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) {
        		int userId = rs.getInt("USER_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, userId, amount, detail, approval);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expenses;
    }
    
    public List<Expenses> findByUserId(int userId){
    	List<Expenses> expensesList = new ArrayList<>();
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM EXPENSES WHERE USER_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, userId);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	while(rs.next()) {
        		int expensesId = rs.getInt("EXPENSES_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, userId, amount, detail, approval);
        		expensesList.add(expenses);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expensesList;
    }
    
    public List<Expenses> expensesOK(int userId, String approval){
    	List<Expenses> expensesList = new ArrayList<>();
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT AMOUNT, DETAIL FROM EXPENSES WHERE USER_ID = ? AND APPROVAL = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, userId);
        	pStmt.setString(2, approval);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	while(rs.next()) {
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		expenses = new Expenses(amount, detail);
        		expensesList.add(expenses);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expensesList;
    }
    
    public boolean insertExpenses(int userId, int amount, String detail) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "INSERT INTO EXPENSES(USER_ID, AMOUNT, DETAIL) VALUES(?, ?, ?)";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, userId);
        	pStmt.setInt(2, amount);
        	pStmt.setString(3, detail);
        	
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
}
