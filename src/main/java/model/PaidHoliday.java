package model;

import java.io.Serializable;
import java.sql.Date;

public class PaidHoliday implements Serializable{
    private int paidHolidayId;
    private int employeeId;
    private double usedDays;
    private Date startDate;
    private Date finishDate;
    private String holidayType;
    private String status;

    public PaidHoliday(int paidHolidayId, int employeeId, double usedDays, 
    		Date startDate, Date finishDate, String holidayType, String status) {
        this.paidHolidayId = paidHolidayId;
        this.employeeId = employeeId;
        this.usedDays = usedDays;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.holidayType =holidayType;
        this.status = status;
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
    
    public String getHolidayType() {
    	return holidayType;
    }
    
    public String getStatus() {
    	return status;
    }
}