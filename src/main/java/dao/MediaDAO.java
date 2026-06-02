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

        String sql ="SELECT media_type,title,content,media_date FROM FORZMEDIA ORDER BY media_date ";

            PreparedStatement pStmt = conn.prepareStatement(sql);

            ResultSet rs = pStmt.executeQuery();
            while(rs.next()) {
            String mediaType = rs.getString("media_type");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String media_date = rs.getString("media_date");
            Media media = new Media(mediaType,title,content,media_date);
            mediaList.add(media);
            }

            

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mediaList;
    }

}
