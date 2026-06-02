package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Port;
import model.User;
import util.PasswordUtil;

public class UserRegisterDAO {

    String JDBC_URL = Port.JDBC_URL;

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
            	user = new User(id, n, p, mail, positionId);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return user;
    }
}