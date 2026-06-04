package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Port;

public class EventDAO {

	private String JDBC_URL = Port.JDBC_URL;

	// イベント一覧取得
	public List<Event> findAll() {

		List<Event> eventList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT * FROM FORZEVENTS ORDER BY event_date";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Event event = new Event();

				event.setEventId(rs.getInt("event_id"));
				event.setUserId(rs.getInt("user_id"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setArea(rs.getString("area"));
				event.setEventDate(rs.getDate("event_date"));
				eventList.add(event);
			}

			System.out.println("取得件数：" + eventList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventList;
	}

	// イベント登録
	public boolean insert(Event event) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "INSERT INTO FORZEVENTS " +
					"(user_id, title, content, area, event_date) " +
					"VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, event.getUserId());
			pStmt.setString(2, event.getTitle());
			pStmt.setString(3, event.getContent());
			pStmt.setString(4, event.getArea());
			pStmt.setDate(5, event.getEventDate());

			int result = pStmt.executeUpdate();

			return result == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}