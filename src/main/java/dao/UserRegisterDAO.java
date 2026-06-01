package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.PasswordUtil;

public class UserRegisterDAO {

    private final String JDBC_URL =
            "jdbc:sqlserver://localhost\\\\SQLEXPRESS:64433;"
            + "databaseName=master;"
            + "integratedSecurity=true;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

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
}