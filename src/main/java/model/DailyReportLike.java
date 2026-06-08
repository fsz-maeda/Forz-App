package model;

import java.util.Date;

public class DailyReportLike {
	private int likeId;
	private int employeeId;
	private int dailyReportId;
	private Date createdAt;
	
	
	public DailyReportLike() {}
	
	public DailyReportLike(int likeId, int employeeId, int dailyReportId,Date createdAt) {
		this.likeId = likeId;
		this.employeeId = employeeId;
		this.dailyReportId = dailyReportId;
		this.createdAt = createdAt;
	}
	
	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getDailyReportId() {
		return dailyReportId;
	}

	public void setDailyReportId(int dailyReportId) {
		this.dailyReportId = dailyReportId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
