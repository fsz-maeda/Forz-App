package model;

import java.io.Serializable;
import java.sql.Date;

public class PaidHoliday implements Serializable{
    private int paidHolidayId;
    private int employeeId;
    private double usedDays;
    private Date startDate;
    private Date finishDate;

    public PaidHoliday(int paidHolidayId, int employeeId, double usedDays, Date startDate, Date finishDate) {
        this.paidHolidayId = paidHolidayId;
        this.employeeId = employeeId;
        this.usedDays = usedDays;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getPaidHolidayId() {
        return paidHolidayId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getUsedDays() {
        return usedDays;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Date getFinishDate() {
        return finishDate;
    }
}