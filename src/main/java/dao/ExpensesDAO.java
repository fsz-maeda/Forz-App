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
    
	//経費テーブルをすべて取得
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
        		int employeeId = rs.getInt("EMPLOYEE_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, employeeId, amount, detail, approval);
        		expensesList.add(expenses);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expensesList;
    }
    
    //指定した経費IDをもつデータを取得
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
        		int employeeId = rs.getInt("EMPLOYEE_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, employeeId, amount, detail, approval);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expenses;
    }
    
    //指定した従業員IDをもつデータをすべて取得
    public List<Expenses> findByEmployeeId(int employeeId){
    	List<Expenses> expensesList = new ArrayList<>();
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM EXPENSES WHERE EMPLOYEE_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, employeeId);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	while(rs.next()) {
        		int expensesId = rs.getInt("EXPENSES_ID");
        		int amount = rs.getInt("AMOUNT");
        		String detail = rs.getString("DETAIL");
        		String approval = rs.getString("APPROVAL");
        		expenses = new Expenses(expensesId, employeeId, amount, detail, approval);
        		expensesList.add(expenses);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return expensesList;
    }
    
    //承認済みの経費をすべて取得
    public List<Expenses> expensesOK(int employeeId, String approval){
    	List<Expenses> expensesList = new ArrayList<>();
    	Expenses expenses = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT AMOUNT, DETAIL FROM EXPENSES WHERE EMPLOYEE_ID = ? AND APPROVAL = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, employeeId);
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
    
    //新規経費情報を登録
    public boolean insertExpenses(int employeeId, int amount, String detail) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "INSERT INTO EXPENSES(EMPLOYEE_ID, AMOUNT, DETAIL) VALUES(?, ?, ?)";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, employeeId);
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
    
    //経費情報を更新
    public boolean updateExpenses(int expensesId, String approval) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql ="UPDATE EXPENSES SET APPROVAL = ? WHERE EXPENSES_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setString(1, approval);
        	pStmt.setInt(2, expensesId);
        	
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
