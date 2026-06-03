package model;

import java.util.Date;

public class DailyReport {

    private int dailyReportId;
    private int userId;
    private String reportType;
    private String title;
    private String content;
    private Date createdAt;
    private int likes;

    private boolean liked; 
    private String userName;   // ⭐追加
    public DailyReport() {}

    public DailyReport(int dailyReportId, int userId, String reportType,String title, String content, Date createdAt,int likes) {
        this.dailyReportId = dailyReportId;
        this.userId = userId;
        this.reportType = reportType;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.likes = likes;
    }

    // getter / setter
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}