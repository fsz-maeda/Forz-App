package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Notice;
import model.PortNo;

public class NoticeDAO {
	String JDBC_URL = PortNo.JDBC_URL;
	public List<Notice> findAll(){
		List<Notice> noticeList = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(JDBC_URL);
		String sql = "SELECT * FROM NOTICE ORDER BY ID DESC";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		while(rs.next()) {
			Notice notice = new Notice();
			notice.setNoticeId(rs.getInt("ID"));
			notice.setTitle(rs.getString("TITLE"));
			notice.setCategory(rs.getString("CATEGORY"));
			notice.setContent(rs.getString("CONTENT"));

	                noticeList.add(notice);
	            }
		conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return noticeList;
	}

}
