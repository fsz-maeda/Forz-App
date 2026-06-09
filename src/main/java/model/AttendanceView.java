package model;

public class AttendanceView {

    private String date;
    private String clockIn;
    private String clockOut;
    private int breakMinutes;
    
    
    
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClockIn() {
		return clockIn;
	}
	public void setClockIn(String clockIn) {
		this.clockIn = clockIn;
	}
	public String getClockOut() {
		return clockOut;
	}
	public void setClockOut(String clockOut) {
		this.clockOut = clockOut;
	}
	public int getBreakMinutes() {
		return breakMinutes;
	}
	public void setBreakMinutes(int breakMinutes) {
		this.breakMinutes = breakMinutes;
	}

}
