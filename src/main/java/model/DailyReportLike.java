package model;

import java.util.Date;

public class DailyReportLike {
	private int likeId;
	private int userId;
	private int dailyReportId;
	private Date createdAt;
	
	
	public DailyReportLike() {}
	
	public DailyReportLike(int likeId, int userId, int dailyReportId,Date createdAt) {
		this.likeId = likeId;
		this.userId = userId;
		this.dailyReportId = dailyReportId;
		this.createdAt = createdAt;
	}
	
	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
