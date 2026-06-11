package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Attendance;
import model.Port;

public class AttendanceDAO {

	private final String JDBC_URL = Port.JDBC_URL;

	// 共通：接続
	private Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(JDBC_URL);
	}

//	今日の出勤を入れる
	public boolean insertClockIn(int employeeId) {

		String sql = """
			INSERT INTO FORZATTENDANCE
			(employee_id, work_date, clock_in, status)
			VALUES (?, ?, GETDATE(), 'WORKING')
		""";

		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);
			ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


//	今日の退勤を入れる
	public boolean updateClockOut(int employeeId) {

		String sql = """
			UPDATE FORZATTENDANCE
			SET clock_out = GETDATE(),
				status = 'FINISHED'
			WHERE employee_id = ?
			AND work_date = CAST(GETDATE() AS DATE)
		""";

		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

//	今日のデータだけ取る
	public Attendance findToday(int employeeId) {

		String sql = """
			SELECT * FROM FORZATTENDANCE
			WHERE employee_id = ?
			AND work_date = CAST(GETDATE() AS DATE)
		""";

		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return map(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

//	日付指定取得
	public Attendance findByDate(int employeeId, String date) {

		String sql = """
			SELECT * FROM FORZATTENDANCE
			WHERE employee_id = ?
			AND work_date = ?
		""";

		try (Connection conn = getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, employeeId);
			ps.setDate(2, java.sql.Date.valueOf(date));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return map(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	一か月分まとめて取る
	public List<Attendance> findBetween(int employeeId, LocalDate start, LocalDate end) {

	    String sql = """
	        SELECT * FROM FORZATTENDANCE
	        WHERE employee_id = ?
	        AND work_date BETWEEN ? AND ?
	        ORDER BY work_date
	    """;

	    List<Attendance> list = new ArrayList<>();

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setDate(2, java.sql.Date.valueOf(start));
	        ps.setDate(3, java.sql.Date.valueOf(end));

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            list.add(map(rs));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
//	javaのオブジェクトに変換
	private Attendance map(ResultSet rs) throws Exception {

		Attendance a = new Attendance();

		a.setAttendanceId(rs.getInt("attendance_id"));
		a.setEmployeeId(rs.getInt("employee_id"));
		a.setWorkDate(rs.getDate("work_date"));
		a.setClockIn(rs.getTimestamp("clock_in"));
		a.setClockOut(rs.getTimestamp("clock_out"));
		a.setBreakMinutes(rs.getInt("break_minutes"));
		a.setStatus(rs.getString("status"));
		a.setCreatedAt(rs.getTimestamp("created_at"));

		return a;
	}
	
//	存在のチェック
	public boolean exists(int employeeId, java.time.LocalDate date) {

	    String sql = """
	        SELECT 1 FROM FORZATTENDANCE
	        WHERE employee_id = ?
	        AND work_date = ?
	    """;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setDate(2, java.sql.Date.valueOf(date));

	        ResultSet rs = ps.executeQuery();

	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
//	既存データ更新
	public boolean updateAttendance(int employeeId,
            java.time.LocalDate date,
            java.sql.Timestamp clockIn,
            java.sql.Timestamp clockOut,
            int breakMinutes) {

		String sql = """
					UPDATE FORZATTENDANCE
					SET clock_in = ?,
					clock_out = ?,
					break_minutes = ?,
					status = 'FINISHED'
					WHERE employee_id = ?
					AND work_date = ?
					""";
		
		try (Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)) {
		
			ps.setTimestamp(1, clockIn);
			ps.setTimestamp(2, clockOut);
			ps.setInt(3, breakMinutes);
			ps.setInt(4, employeeId);
			ps.setDate(5, java.sql.Date.valueOf(date));
			
			return ps.executeUpdate() > 0;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
//	新規登録
	public boolean insertAttendance(int employeeId,
        java.time.LocalDate date,
        java.sql.Timestamp clockIn,
        java.sql.Timestamp clockOut,
        int breakMinutes) {

		String sql = """
				INSERT INTO FORZATTENDANCE
				(employee_id, work_date, clock_in, clock_out, break_minutes, status)
				VALUES (?, ?, ?, ?, ?, 'FINISHED')
				""";
		
		try (Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)) {
		
			ps.setInt(1, employeeId);
			ps.setDate(2, java.sql.Date.valueOf(date));
			ps.setTimestamp(3, clockIn);
			ps.setTimestamp(4, clockOut);
			ps.setInt(5, breakMinutes);
			
			return ps.executeUpdate() > 0;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return false;
		}
	
	
	public boolean saveAttendance(int employeeId, String date,
            Timestamp clockIn,
            Timestamp clockOut,
            int breakMinutes) {

	String sql = """
			MERGE FORZATTENDANCE AS target
			USING (SELECT ? AS employee_id, ? AS work_date) AS src
			ON (target.employee_id = src.employee_id
			AND target.work_date = src.work_date)
			
			WHEN MATCHED THEN
			UPDATE SET
			clock_in = ?,
			clock_out = ?,
			break_minutes = ?,
			status = 'FINISHED'
			
			WHEN NOT MATCHED THEN
			INSERT (employee_id, work_date, clock_in, clock_out, break_minutes, status)
			VALUES (?, ?, ?, ?, ?, 'FINISHED');
			""";
	
	try (Connection conn = getConnection();
	PreparedStatement ps = conn.prepareStatement(sql)) {
	
		ps.setInt(1, employeeId);
		ps.setDate(2, java.sql.Date.valueOf(date));
		
		ps.setTimestamp(3, clockIn);
		ps.setTimestamp(4, clockOut);
		ps.setInt(5, breakMinutes);
		
		ps.setInt(6, employeeId);
		ps.setDate(7, java.sql.Date.valueOf(date));
		ps.setTimestamp(8, clockIn);
		ps.setTimestamp(9, clockOut);
		ps.setInt(10, breakMinutes);
		
		return ps.executeUpdate() > 0;
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	
		return false;
	}
	
	
//	月の承認
	public boolean approveMonth(int employeeId, int year, int month) {

        String sql = """
            UPDATE FORZATTENDANCE
            SET approval_status = 'APPROVED'
            WHERE employee_id = ?
            AND YEAR(work_date) = ?
            AND MONTH(work_date) = ?
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, year);
            ps.setInt(3, month);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
	
	
//	承認チェック
	public boolean isMonthApproved(int employeeId, int year, int month) {

	    String sql = """
	        SELECT COUNT(*) 
	        FROM FORZATTENDANCE
	        WHERE employee_id = ?
	        AND YEAR(work_date) = ?
	        AND MONTH(work_date) = ?
	        AND approval_status = 'APPROVED'
	    """;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setInt(2, year);
	        ps.setInt(3, month);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
//	承認解除
	public boolean unapproveMonth(int employeeId, int year, int month) {

	    String sql = """
	        UPDATE FORZATTENDANCE
	        SET approval_status = 'PENDING'
	        WHERE employee_id = ?
	        AND YEAR(work_date) = ?
	        AND MONTH(work_date) = ?
	    """;

	    try (Connection conn = getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setInt(2, year);
	        ps.setInt(3, month);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	

}