package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Attendance {

    private int attendanceId;
    private int employeeId;

    private Date workDate;
    private Timestamp clockIn;
    private Timestamp clockOut;

    private int breakMinutes;
    private Timestamp createdAt;
    private String status;

    public Attendance() {}

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Timestamp getClockIn() {
        return clockIn;
    }

    public void setClockIn(Timestamp clockIn) {
        this.clockIn = clockIn;
    }

    public Timestamp getClockOut() {
        return clockOut;
    }

    public void setClockOut(Timestamp clockOut) {
        this.clockOut = clockOut;
    }

    public int getBreakMinutes() {
        return breakMinutes;
    }

    public void setBreakMinutes(int breakMinutes) {
        this.breakMinutes = breakMinutes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}