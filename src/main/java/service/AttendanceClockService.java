package service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import dao.AttendanceDAO;

public class AttendanceClockService {

    public void saveAttendance(int employeeId, String date,
                               String clockInStr, String clockOutStr,
                               int breakMinutes) {

//      2026-06-09から日付に変換
        LocalDate d = LocalDate.parse(date);
        
        Timestamp clockIn = null;
        Timestamp clockOut = null;
        

        try {

//          09:00から時間に変換
            if (clockInStr != null && !clockInStr.isEmpty()) {
                clockIn = Timestamp.valueOf(
                        LocalDateTime.of(d, LocalTime.parse(clockInStr)));
            }

            if (clockOutStr != null && !clockOutStr.isEmpty()) {
                clockOut = Timestamp.valueOf(
                        LocalDateTime.of(d, LocalTime.parse(clockOutStr)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        AttendanceDAO dao = new AttendanceDAO();

        boolean exists = dao.exists(employeeId, d);

        if (exists) {
            dao.updateAttendance(employeeId, d, clockIn, clockOut, breakMinutes);
        } else {
            dao.insertAttendance(employeeId, d, clockIn, clockOut, breakMinutes);
        }
    }
}