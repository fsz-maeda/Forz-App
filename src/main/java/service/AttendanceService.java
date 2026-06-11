package service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Attendance;
import model.AttendanceView;

public class AttendanceService {

//	その日の勤務時間を計算する
    public double calcWorkHours(Attendance a) {
    	
//    	どちらかなければ０時間
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

//  月合計計算
    public double calcMonthlyTotal(List<Attendance> list) {
        return list.stream()
                .mapToDouble(this::calcWorkHours)
                .sum();
    }
    
//  時間のフォーマットに変換
    public String formatHours(double hours) {

        int h = (int) hours;
        int m = (int) Math.round((hours - h) * 60);

        return h + "時間" + m + "分";
    }
    
    
//  画面変換用
    public AttendanceView toView(Attendance a) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

        AttendanceView v = new AttendanceView();

//      日付
        v.setDate(a.getWorkDate().toString());

//      出勤時間
        v.setClockIn(
            a.getClockIn() != null
                ? a.getClockIn().toLocalDateTime().format(fmt)
                : ""
        );

//      退勤時間
        v.setClockOut(
            a.getClockOut() != null
                ? a.getClockOut().toLocalDateTime().format(fmt)
                : ""
        );

//      休憩時間
        v.setBreakMinutes(a.getBreakMinutes());

        return v;
    }
    
    
}