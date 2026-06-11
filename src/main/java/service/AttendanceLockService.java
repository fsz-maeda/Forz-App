package service;

import java.time.LocalDate;

import dao.SystemSettingDAO;

public class AttendanceLockService {

    public boolean isLocked(
            LocalDate date,
            boolean isAdmin,
            boolean approved,
            int year,
            int month
    ) {

        if (isAdmin) return false;

        SystemSettingDAO dao = new SystemSettingDAO();
        int closeDay = dao.getCloseDay();

        LocalDate today = LocalDate.now();

//      今回の締め日
        LocalDate closeDate = LocalDate.of(
                today.getYear(),
                today.getMonth(),
                closeDay
        );

//      ロック開始日（1ヶ月前の翌日）
        LocalDate startLock = closeDate.minusMonths(1).plusDays(1);

//      過去（締め済み期間）は全部ロック
        if (date.isBefore(startLock)) {
            return true;
        }

//      締め対象期間はロック
        if (!date.isAfter(closeDate)) {
            return true;
        }

        return false;
    }
}