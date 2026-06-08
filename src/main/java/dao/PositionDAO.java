package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Port;
import model.Position;

public class PositionDAO {
	String JDBC_URL = Port.JDBC_URL;
    
    public List<Position> findAll(){
    	List<Position> positionList = new ArrayList<>();
    	Position position = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM POSITION";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	while(rs.next()) {
        		int positionId = rs.getInt("POSITION_ID");
        		String positionName = rs.getString("POSITION_NAME");
        		position = new Position(positionId, positionName);
        		positionList.add(position);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return positionList;
    }
    
    public Position findByPositionId(int positionId) {
    	Position position = null;
    	
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT POSITION_NAME FROM POSITION WHERE POSITION_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, positionId);
        	
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) {
        		String positionName = rs.getString("POSITION_NAME");
        		position = new Position(positionId, positionName);
        	}
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return position;
    }
    
    public boolean insertPosition(int positionId, String positionName) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "INSERT INTO POSITION (POSITION_ID, POSITION_NAME) VALUES(?, ?)";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, positionId);
        	pStmt.setString(2, positionName);
        	
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
    
    public boolean updatePosition(int newPositionId, String positionName, int positionId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "UPDATE POSITION SET POSITION_ID = ?, POSITION_NAME = ? WHERE POSITION_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, newPositionId);
        	pStmt.setString(2, positionName);
        	pStmt.setInt(3, positionId);
        	
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
    
    public boolean deletePosition(int positionId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "DELETE FROM POSITION WHERE POSITION_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
        	pStmt.setInt(1, positionId);
        	
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
