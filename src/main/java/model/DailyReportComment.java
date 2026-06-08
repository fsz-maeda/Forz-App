package model;

import java.util.Date;

public class DailyReportComment {
    private int commentId;
    private int dailyReportId;
    private int employeeId;
	private String employeeName;
    private String commentText;
    private Date createdAt;

    
    public DailyReportComment() {}
    
    public DailyReportComment(int commentId, int dailyReportId, int employeeId, String employeeName, String commentText,Date createdAt) {
    	this.commentId = commentId;
    	this.dailyReportId = dailyReportId;
    	this.employeeId = employeeId;
    	this.employeeName = employeeName;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
