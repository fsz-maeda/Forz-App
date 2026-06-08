package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Comment;
import model.Port;

public class CommentDAO {
	String JDBC_URL = Port.JDBC_URL;

	public boolean insert(Comment comment) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		String sql = "INSERT INTO EVENT_COMMENTS " +
				"(event_id,user_id,comment) " +
				"VALUES(?,?,?)";

		try (Connection conn = DriverManager.getConnection(JDBC_URL);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, comment.getEventId());
			pStmt.setInt(2, comment.getUserId());
			pStmt.setString(3, comment.getComment());

			return pStmt.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// イベントごとのコメント一覧取得
	public List<Comment> findByEventId(int eventId) {

		List<Comment> commentList = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT * "
					+ "FROM EVENT_COMMENTS "
					+ "WHERE event_id = ? "
					+ "ORDER BY created_at";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, eventId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Comment comment = new Comment();

				comment.setCommentId(
						rs.getInt("comment_id"));

				comment.setEventId(
						rs.getInt("event_id"));

				comment.setUserId(
						rs.getInt("user_id"));

				comment.setComment(
						rs.getString("comment"));
				commentList.add(comment);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return commentList;
	}
}