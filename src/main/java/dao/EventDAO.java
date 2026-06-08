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
				event.setEmployeeId(rs.getInt("employee_id"));
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

	// ページごと取得
	public List<Event> findPage(int offset, int pageSize) {

		List<Event> eventList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT * " +
					"FROM FORZEVENTS " +
					"ORDER BY event_date DESC " +
					"OFFSET ? ROWS " +
					"FETCH NEXT ? ROWS ONLY";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, offset);
			pStmt.setInt(2, pageSize);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Event event = new Event();

				event.setEventId(rs.getInt("event_id"));
				event.setEmployeeId(rs.getInt("employee_id"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setArea(rs.getString("area"));
				event.setEventDate(rs.getDate("event_date"));

				eventList.add(event);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventList;
	}

	// 総件数取得
	public int countAll() {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT COUNT(*) FROM FORZEVENTS";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// イベント登録
	public boolean insert(Event event) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "INSERT INTO FORZEVENTS " +
					"(employee_id, title, content, area, event_date) " +
					"VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, event.getEmployeeId());
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

	public Event findById(int eventId) {

		Event event = null;

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "SELECT * FROM FORZEVENTS WHERE event_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, eventId);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {

				event = new Event();

				event.setEventId(rs.getInt("event_id"));
				event.setEmployeeId(rs.getInt("employee_id"));
				event.setTitle(rs.getString("title"));
				event.setContent(rs.getString("content"));
				event.setArea(rs.getString("area"));
				event.setEventDate(rs.getDate("event_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}

	// イベント削除
	public boolean delete(int eventId) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL)) {

			String sql = "DELETE FROM FORZEVENTS WHERE event_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, eventId);

			return pStmt.executeUpdate() == 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}