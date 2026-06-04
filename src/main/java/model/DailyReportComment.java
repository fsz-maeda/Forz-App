package model;

import java.util.Date;

public class DailyReportComment {
    private int commentId;
    private int dailyReportId;
    private int userId;
    private String userName;
    private String commentText;
    private Date createdAt;
    
    public DailyReportComment() {}
    
    public DailyReportComment(int commentId, int dailyReportId, int userId, String userName, String commentText,Date createdAt) {
    	this.commentId = commentId;
    	this.dailyReportId = dailyReportId;
    	this.userId = userId;
    	this.userName = userName;
    	this.commentText = commentText;
    	this.createdAt = createdAt;
    }
    
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getDailyReportId() {
		return dailyReportId;
	}
	public void setDailyReportId(int dailyReportId) {
		this.dailyReportId = dailyReportId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
