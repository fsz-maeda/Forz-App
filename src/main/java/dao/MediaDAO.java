package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Media;
import model.Port;

public class MediaDAO {
	
	String JDBC_URL = Port.JDBC_URL;

    public List<Media> findAll(Employee loginUser) {
    	List<Media> mediaList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

        String sql ="SELECT ID,MEDIA_TYPE,TITLE,CONTENT,created_at FROM FORZMEDIA WHERE DEPARTMENT_ID = ? ORDER BY created_at DESC";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setInt(1,loginUser.getDepartment());;
            

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
    
    public boolean mediaRegist(Media media, int departmentId,Employee em) {
    	 try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         } catch (ClassNotFoundException e) {
             throw new IllegalStateException("JDBCドライバを読み込めませんでした");
         }

         try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

         String sql ="INSERT INTO FORZMEDIA(USER_ID,MEDIA_TYPE,TITLE,CONTENT,DEPARTMENT_ID) VALUES(?,?,?,?,?)";
         PreparedStatement pStmt = conn.prepareStatement(sql);
         
         pStmt.setInt(1,em.getEmployeeId());
         pStmt.setString(2,media.getMediaType());
         pStmt.setString(3,media.getTitle());
         pStmt.setString(4,media.getContent());
         pStmt.setInt(5,departmentId);
         
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

        String sql ="SELECT CONTENT,TITLE,USER_ID FROM FORZMEDIA WHERE ID =?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
           pStmt.setInt(1,id); 
        
            
           ResultSet rs = pStmt.executeQuery();
           
           if(rs.next()) {
           String content = rs.getString("CONTENT");
           String title = rs.getString("TITLE");
           int userId = rs.getInt("USER_ID"); 
           
           media = new Media(id,content,title,userId);
           
           }
           
    }catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
       return media;
        }
    
    public boolean mediaUpdate(Media media) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	
            String sql = "UPDATE FORZMEDIA SET TITLE = ?, CONTENT = ? WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, media.getTitle());
            pStmt.setString(2, media.getContent());
            pStmt.setInt(3, media.getId());
            
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean mediaDelete(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            
            String sql = "DELETE FROM FORZMEDIA WHERE ID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setInt(1, id);
            
            int result = pStmt.executeUpdate();
            if (result != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<Media> findByCategory(Employee loginUser, String searchType) {
        List<Media> mediaList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            // 部署IDに加えて、MEDIA_TYPE も条件（WHERE）に指定する
            String sql = "SELECT ID, MEDIA_TYPE, TITLE, CONTENT, created_at, USER_ID FROM FORZMEDIA WHERE DEPARTMENT_ID = ? AND MEDIA_TYPE = ? ORDER BY created_at DESC";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, loginUser.getDepartment());
            pStmt.setString(2, searchType); // 👈 絞り込みたいタイプをセット

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String mediaType = rs.getString("media_type");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String created_at = rs.getString("created_at");
                int userId = rs.getInt("USER_ID");
                
                Media media = new Media(id, mediaType, title, content, created_at, userId);
                mediaList.add(media);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mediaList;
    }


    
}







