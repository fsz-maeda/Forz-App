package model;
import java.io.Serializable;
import java.sql.Date;

public class MediaByEmployeeName implements Serializable{
	private int ID;
	private String name; 
	private String mediaType;
	private String title;
	private String content;
	private Date createdAt;
	private int departmentId;
	
	public MediaByEmployeeName(int id, String name, String mediaType, String title, String content, 
			Date createdAt, int departmentId) {
		this.ID = id;
		this.name = name;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.departmentId = departmentId;
	}
	
	public int getId() {
		return ID;
	}
	
	public String getName() { 
		return name; 
	}
	
	public String getMediaType() {
		return mediaType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public int getDepartmentId() {
		return departmentId;
	}
}
