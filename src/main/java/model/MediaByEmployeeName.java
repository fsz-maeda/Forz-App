package model;
import java.io.Serializable;

public class MediaByEmployeeName implements Serializable{
	private int ID;
	private String name; 
	private String mediaType;
	private String title;
	private String content;
	private String mediaDate;
	
	public MediaByEmployeeName(int ID, String name, String mediaType, String title,
			String content,String mediaDate) {
		this.ID = ID;
		this.name = name;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.mediaDate = mediaDate;
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
	
	public String getMediaDate() {
		return mediaDate;
	}
}
