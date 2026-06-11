package service;

import java.time.LocalDate;
import java.util.List;

import dao.AttendanceDAO;
import model.Attendance;

//DBアクセスをまとめてる層
public class AttendanceQueryService {

    private final AttendanceDAO dao = new AttendanceDAO();

//  今日の勤怠取得
    public Attendance findToday(int employeeId) {
        return dao.findToday(employeeId);
    }

//  日付の取得
    public Attendance findByDate(int employeeId, String date) {
        return dao.findByDate(employeeId, date);
    }

//  月単位の取得
    public List<Attendance> findMonthly(int employeeId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return dao.findBetween(employeeId, start, end);
    }
}