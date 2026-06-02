package model;

public class Notice {
	private int noticeId;
	private String title;
	private String category;
	private String content;
	
	public int getNoticeId() {return noticeId;}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public String getTitle() {return title;}
	public void setTitle(String title) {
		this.title =title;
	}
	public String getCategory() {return category;}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {return content;}
	public void setContent(String content) {
		this.content = content;
	}

}
