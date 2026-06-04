package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Media;
import model.Port;

public class MediaDAO {
	
	String JDBC_URL = Port.JDBC_URL;

    public List<Media> findAll() {
    	List<Media> mediaList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

        String sql ="SELECT media_type,title,content,created_at FROM FORZMEDIA where USER_ID = 2 ORDER BY media_date DESC";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()) {
            String mediaType = rs.getString("media_type");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String media_date = rs.getString("created_at");
            Media media = new Media(mediaType,title,content,media_date);
            mediaList.add(media);
            }

            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mediaList;
    }
    
    public boolean mediaRegist(Media media, int id) {
    	 try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         } catch (ClassNotFoundException e) {
             throw new IllegalStateException("JDBCドライバを読み込めませんでした");
         }

         try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

         String sql ="INSERT INTO FORZMEDIA(USER_ID,MEDIA_TYPE,TITLE,CONTENT) VALUES(?,?,?,?)";
         PreparedStatement pStmt = conn.prepareStatement(sql);
         
         pStmt.setInt(1,id);
         pStmt.setString(2,media.getMediaType());
         pStmt.setString(3,media.getTitle());
         pStmt.setString(4,media.getContent());
         
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






