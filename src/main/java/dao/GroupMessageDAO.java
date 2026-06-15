package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.GroupMessage;
import model.Port;

public class GroupMessageDAO {

	String JDBC_URL = Port.JDBC_URL;

	// メッセージ一覧取得
	public List<GroupMessage> getMessages(
			int groupId) {

		List<GroupMessage> list = new ArrayList<>();

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(
					JDBC_URL);

			String sql = "SELECT " +
					"gc.CHAT_ID, " +
					"gc.GROUP_ID, " +
					"gc.SENDER_ID, " +
					"gc.MESSAGE, " +
					"gc.SEND_TIME, " +
					"e.NAME, " +
					"e.PHOTO_PATH " +
					"FROM GROUP_CHAT gc " +
					"INNER JOIN EMPLOYEE e " +
					"ON gc.SENDER_ID = e.ID " +
					"WHERE gc.GROUP_ID = ? " +
					"ORDER BY gc.SEND_TIME";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, groupId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				GroupMessage msg = new GroupMessage();

				msg.setChatId(
						rs.getInt(
								"CHAT_ID"));

				msg.setGroupId(
						rs.getInt(
								"GROUP_ID"));

				msg.setSenderId(
						rs.getInt(
								"SENDER_ID"));

				msg.setMessage(
						rs.getString(
								"MESSAGE"));

				msg.setSendTime(
						rs.getString(
								"SEND_TIME"));

				msg.setSenderName(
						rs.getString(
								"NAME"));

				msg.setPhotoPath(
						rs.getString(
								"PHOTO_PATH"));

				list.add(msg);
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	// メッセージ送信
	public boolean sendMessage(
			GroupMessage msg) {

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(
					JDBC_URL);

			String sql = "INSERT INTO GROUP_CHAT " +
					"(GROUP_ID, SENDER_ID, MESSAGE) " +
					"VALUES (?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(
					1,
					msg.getGroupId());

			pStmt.setInt(
					2,
					msg.getSenderId());

			pStmt.setString(
					3,
					msg.getMessage());

			int result = pStmt.executeUpdate();

			conn.close();

			return result == 1;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
}