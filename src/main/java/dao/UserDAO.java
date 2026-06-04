package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Port;
import model.User;
import model.UserPosition;
import util.PasswordUtil;

public class UserDAO {

    String JDBC_URL = Port.JDBC_URL;
    
    public List<User> findAll(){
    	List<User> userList = new ArrayList<>();
    	User user = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT ID, NAME, PASS, MAIL, position_id FROM FORZUSERS";
        	
        	 PreparedStatement pStmt = conn.prepareStatement(sql);
        	 
        	 ResultSet rs = pStmt.executeQuery();
        	 
        	 while(rs.next()) {
        		 int id = rs.getInt("ID");
        		 String name = rs.getString("NAME");
        		 String pass = rs.getString("PASS");
        		 String mail = rs.getString("MAIL");
        		 int positionId = rs.getInt("position_id");
        		 user = new User(id, name, pass, mail, positionId);
        		 userList.add(user);
        	 }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return userList;
    }
    
    public List<UserPosition> findPositionName(){
    	UserPosition userPosition = null;
    	List<UserPosition> userPositionList = new ArrayList<>();
    	
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
        	
        	while(rs.next()) {
        		int id =rs.getInt("ID");
        		String name = rs.getString("NAME");
        		String pass = rs.getString("PASS");
        		String mail = rs.getString("MAIL");
        		String positionName = rs.getString("POSITION_NAME");
        		userPosition = new UserPosition(id, name, pass, mail, positionName);
        		userPositionList.add(userPosition);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return userPositionList;
    }

    public boolean registerUser(String name, String pass, String mail) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

            // パスワードをハッシュ化
            String hashedPass = PasswordUtil.hashPassword(pass);

            String sql =
                    "INSERT INTO FORZUSERS(NAME, PASS, MAIL) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, hashedPass);
            pStmt.setString(3, mail);

            int result = pStmt.executeUpdate();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean login(String name, String pass) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String hashedPass = PasswordUtil.hashPassword(pass);
        	
        	String sql = "SELECT * FROM FORZUSERS WHERE NAME = ? AND PASS = ?";
        	
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
    
    public User findByNameAndPass(String name, String pass) {
    	User user = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String hashedPass = PasswordUtil.hashPassword(pass);
        	
        	String sql = "SELECT * FROM FORZUSERS WHERE NAME = ? AND PASS = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, hashedPass);
            
            
            ResultSet rs = pStmt.executeQuery();
            
            if(rs.next()) {
            	int id = rs.getInt("ID");
            	String n = rs.getString("NAME");
            	String p = rs.getString("PASS");
            	String mail = rs.getString("MAIL");
            	int positionId = rs.getInt("position_id");
            	int departmentId = rs.getInt("DEPARTMENT_ID");
            	user = new User(id, n, p, mail, positionId,departmentId);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }
    
    public User findByUserId(int userId) {
    	User user = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT ID, NAME, PASS, MAIL, position_id FROM FORZUSERS WHERE ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            
            ResultSet rs = pStmt.executeQuery();
            
            if(rs.next()) {
            	int id = rs.getInt("ID");
            	String n = rs.getString("NAME");
            	String p = rs.getString("PASS");
            	String mail = rs.getString("MAIL");
            	int positionId = rs.getInt("position_id");
            	user = new User(id, n, p, mail, positionId);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }
    
    public boolean updateUserPosition(String positionName, int userId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "UPDATE FORZUSERS "
        			+ "SET position_id = POSITION.POSITION_ID "
        			+ "FROM FORZUSERS "
        			+ "JOIN POSITION ON POSITION.POSITION_NAME = ? "
        			+ "WHERE FORZUSERS.ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, positionName);
            pStmt.setInt(2, userId);
            
            int result = pStmt.executeUpdate();
            return result == 1;
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteUser(int userId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "DELETE FROM FORZUSERS WHERE ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userId);
            
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