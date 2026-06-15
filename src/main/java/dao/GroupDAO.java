package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.Port;

public class GroupDAO {

	String JDBC_URL = Port.JDBC_URL;

	// グループ作成
	public boolean createGroup(
			Group group) {

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(
					JDBC_URL);

			String sql = "INSERT INTO CHAT_GROUP " +
					"(GROUP_NAME, CREATED_BY) " +
					"VALUES (?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(
					1,
					group.getGroupName());

			pStmt.setInt(
					2,
					group.getCreatedBy());

			int result = pStmt.executeUpdate();

			conn.close();

			return result == 1;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	// グループ一覧取得
	public List<Group> getAllGroups() {

		List<Group> groupList = new ArrayList<>();

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(
					JDBC_URL);

			String sql = "SELECT * " +
					"FROM CHAT_GROUP " +
					"ORDER BY GROUP_ID";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Group group = new Group();

				group.setGroupId(
						rs.getInt(
								"GROUP_ID"));

				group.setGroupName(
						rs.getString(
								"GROUP_NAME"));

				group.setCreatedBy(
						rs.getInt(
								"CREATED_BY"));

				group.setCreatedAt(
						rs.getString(
								"CREATED_AT"));

				groupList.add(group);
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return groupList;
	}

	// グループ取得
	public Group getGroupById(
			int groupId) {

		Group group = null;

		try {

			Class.forName(
					"com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection conn = DriverManager.getConnection(
					JDBC_URL);

			String sql = "SELECT * " +
					"FROM CHAT_GROUP " +
					"WHERE GROUP_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(
					1,
					groupId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {

				group = new Group();

				group.setGroupId(
						rs.getInt(
								"GROUP_ID"));

				group.setGroupName(
						rs.getString(
								"GROUP_NAME"));

				group.setCreatedBy(
						rs.getInt(
								"CREATED_BY"));

				group.setCreatedAt(
						rs.getString(
								"CREATED_AT"));
			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return group;
	}
}