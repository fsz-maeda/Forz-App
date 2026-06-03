package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import model.Chat;
import model.Port;

public class ChatDAO {
	String JDBC_URL = Port.JDBC_URL;
	
	public boolean insert(Chat chat) {
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		Connection conn = DriverManager.getConnection(JDBC_URL);
String sql = "INSERT INTO CHAT(SENDER_ID, RECEIVER_ID, MESSAGE) VALUES(?,?,?)";
	PreparedStatement pStmt = conn.prepareStatement(sql);
	
	pStmt.setInt(1, chat.getSenderId());
	pStmt.setInt(2, chat.getReceiverId());
	pStmt.setString(3, chat.getMessage());
	
	int result = pStmt.executeUpdate();
	conn.close();
	return result == 1;
	}catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	
		
	}

}
