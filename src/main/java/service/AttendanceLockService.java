package service;

import java.time.LocalDate;

import dao.SystemSettingDAO;

public class AttendanceLockService {

    private final SystemSettingDAO settingDAO = new SystemSettingDAO();

    public boolean isLocked(
            LocalDate date,
            boolean isAdmin,
            boolean approved,
            int year,
            int month
    ) {

        if (isAdmin) return false;

        LocalDate baseDate = LocalDate.of(year, month, 1);

        int closeDay = settingDAO.getCloseDay();

        LocalDate closeDate = LocalDate.of(
                baseDate.getYear(),
                baseDate.getMonth(),
                Math.min(closeDay, baseDate.lengthOfMonth())
        );

        // ①締め日前は編集OK
        if (!LocalDate.now().isAfter(closeDate)) {
            return false;
        }

        // ②承認済み → 月全ロック
        if (approved) {
            return true; // ← シンプルでOK
        }

        // ③未承認 → 過去月だけロック
        return date.isBefore(baseDate);
    }
}