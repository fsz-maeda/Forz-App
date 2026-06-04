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
import model.User;

public class MediaDAO {
	
	String JDBC_URL = Port.JDBC_URL;

    public List<Media> findAll(User loginUser) {
    	List<Media> mediaList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

        String sql ="SELECT ID,MEDIA_TYPE,TITLE,CONTENT,created_at FROM FORZMEDIA WHERE DEPARTMENT_ID = ? ORDER BY created_at DESC";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setInt(1,loginUser.getDepartmentId());;
            

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()) {
            int id = rs.getInt("ID");
            String mediaType = rs.getString("media_type");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String created_at = rs.getString("created_at");
            Media media = new Media(id,mediaType,title,content,created_at);
            mediaList.add(media);
            }

            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mediaList;
    }
    
    public boolean mediaRegist(Media media, int departmentId) {
    	 try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         } catch (ClassNotFoundException e) {
             throw new IllegalStateException("JDBCドライバを読み込めませんでした");
         }

         try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

         String sql ="INSERT INTO FORZMEDIA(MEDIA_TYPE,TITLE,CONTENT,DEPARTMENT_ID) VALUES(?,?,?,?)";
         PreparedStatement pStmt = conn.prepareStatement(sql);
         
         pStmt.setString(1,media.getMediaType());
         pStmt.setString(2,media.getTitle());
         pStmt.setString(3,media.getContent());
         pStmt.setInt(4,departmentId);
         
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
    
    public Media articleFind(int id) {
    	Media media =null;
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

        String sql ="SELECT CONTENT,TITLE FROM FORZMEDIA WHERE ID =?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
           pStmt.setInt(1,id); 
        
            
           ResultSet rs = pStmt.executeQuery();
           
           if(rs.next()) {
           String content = rs.getString("CONTENT");
           String title = rs.getString("TITLE");
           
           media = new Media(content,title);
           
           }
           
    }catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
       return media;
        }    
    
}







