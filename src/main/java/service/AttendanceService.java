package service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Attendance;
import model.AttendanceView;

public class AttendanceService {

    public double calcWorkHours(Attendance a) {
    	
        if (a.getClockIn() == null || a.getClockOut() == null) {
            return 0;
        }

//      ミリ秒を出して60000で分に変換
        long minutes =
            (a.getClockOut().getTime() - a.getClockIn().getTime()) / 60000;

        double hours = minutes / 60.0;
        
//      休憩を引く
        hours -= a.getBreakMinutes() / 60.0;

        return Math.max(0, hours);
    }

//  全部足す処理
    public double calcMonthlyTotal(List<Attendance> list) {
        return list.stream()
                .mapToDouble(this::calcWorkHours)
                .sum();
    }
    
    public String formatHours(double hours) {

        int h = (int) hours;
        int m = (int) Math.round((hours - h) * 60);

        return h + "時間" + m + "分";
    }
    
    public AttendanceView toView(Attendance a) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

        AttendanceView v = new AttendanceView();

        v.setDate(a.getWorkDate().toString());

        v.setClockIn(
            a.getClockIn() != null
                ? a.getClockIn().toLocalDateTime().format(fmt)
                : ""
        );

        v.setClockOut(
            a.getClockOut() != null
                ? a.getClockOut().toLocalDateTime().format(fmt)
                : ""
        );

        v.setBreakMinutes(a.getBreakMinutes());

        return v;
    }
    
    
}