package model;
import java.io.Serializable;

public class Media implements Serializable{

	private int ID;
	private int employeeId; 
	private String mediaType;
	private String title;
	private String content;
	private String mediaDate;
	private int likesCount;
	
	public Media(int ID,int employeeId,String mediaType,String title,String content,String mediaDate) {
		this.ID = ID;
		this.employeeId = employeeId;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.mediaDate = mediaDate; 
	}
	
	public Media(String mediaType,String title,String content) {
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
	}
	public Media(int ID,String content, String title,int employeeId) {
		this.ID = ID;
		this.content = content;
		this.title = title;
		this.employeeId = employeeId; 
	}
	
	public Media(int id, String mediaType, String title, String content, String created_at, int employeeId) {
		this.ID = id;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.mediaDate = created_at;
		this.employeeId = employeeId; 
	}

	public int getId() {
		return ID;
	}
	
	public int getEmployeeId() { 
		return employeeId; 
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
	
	public String getMediaDate() {
		return mediaDate;
	}
	
	public int getLikesCount() {
	    return likesCount;
	}

	public void setLikesCount(int likesCount) {
	    this.likesCount = likesCount;
	}
}
