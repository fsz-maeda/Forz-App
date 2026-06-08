package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Media;
import model.MediaComment;
import model.Port;

public class MediaCommentDAO {

	String JDBC_URL = Port.JDBC_URL;

    public void postComment(MediaComment mc) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

        String sql ="INSERT INTO MEDIACOMMENT(ID,NAME,COMMENT) VALUES(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setInt(1,mc.getId());
            pStmt.setString(2,mc.getName());
            pStmt.setString(3,mc.getComment());
            
            
            
            pStmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
       
}

	public List<MediaComment> findComment(Media media) {
		List<MediaComment>commentlist = new ArrayList();
		 try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        } catch (ClassNotFoundException e) {
	            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	        }

	        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

	        String sql ="SELECT * FROM MEDIACOMMENT WHERE ID = ?";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            
	            pStmt.setInt(1,media.getId());
	            
	          ResultSet rs = pStmt.executeQuery();
	          while(rs.next()) {
	        	  int id = rs.getInt("ID");
	        	  String name = rs.getString("NAME");
	        	  String comment = rs.getString("COMMENT");
	        	  
	        	  MediaComment mc = new MediaComment(id,name,comment);
	        	  commentlist.add(mc);
	          }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return commentlist;
	}
}
