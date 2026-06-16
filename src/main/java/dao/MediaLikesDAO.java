package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.MediaLikeStatus;
import model.Port;

public class MediaLikesDAO {

    String JDBC_URL = Port.JDBC_URL;

    // いいね数取得
    public int countLikes(int mediaId) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT COUNT(*) FROM FORZMEDIALIKES WHERE FORZMEDIA_ID = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, mediaId);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ログインユーザーがいいね済みか
    public boolean isLiked(int employeeId, int mediaId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "SELECT * FROM FORZMEDIALIKES WHERE EMPLOYEE_ID = ? AND FORZMEDIA_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employeeId);
            pStmt.setInt(2, mediaId);

            ResultSet rs = pStmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // いいね追加
    public void addLike(int employeeId, int mediaId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "INSERT INTO FORZMEDIALIKES (EMPLOYEE_ID, FORZMEDIA_ID) VALUES (?, ?)";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employeeId);
            pStmt.setInt(2, mediaId);

            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // いいね解除
    public void removeLike(int employeeId, int mediaId) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
        	String sql = "DELETE FROM FORZMEDIALIKES WHERE EMPLOYEE_ID = ? AND FORZMEDIA_ID = ?";
        	
        	PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, employeeId);
            pStmt.setInt(2, mediaId);

            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 状態取得
    public MediaLikeStatus getStatus(int employeeId, int mediaId) {
        int likes = countLikes(mediaId);
        boolean liked = isLiked(employeeId, mediaId);

        return new MediaLikeStatus(likes, liked);
    }
}