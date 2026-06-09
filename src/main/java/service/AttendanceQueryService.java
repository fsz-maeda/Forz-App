package service;

import java.time.LocalDate;
import java.util.List;

import dao.AttendanceDAO;
import model.Attendance;

public class AttendanceQueryService {

    private final AttendanceDAO dao = new AttendanceDAO();

    public Attendance findToday(int employeeId) {
        return dao.findToday(employeeId);
    }

    public Attendance findByDate(int employeeId, String date) {
        return dao.findByDate(employeeId, date);
    }

    public List<Attendance> findMonthly(int employeeId, int year, int month) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return dao.findBetween(employeeId, start, end);
    }
}