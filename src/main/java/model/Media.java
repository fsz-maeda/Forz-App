package model;
import java.io.Serializable;

public class Media implements Serializable{

	private int ID;
	private int userId; 
	private String mediaType;
	private String title;
	private String content;
	private String mediaDate;
	
	public Media(int ID,String mediaType,String title,String content,String mediaDate) {
		this.ID = ID;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.mediaDate = mediaDate;
		this.userId = userId; 
	}
	
	public Media(String mediaType,String title,String content) {
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
	}
	public Media(int ID,String content, String title,int userId) {
		this.ID = ID;
		this.content = content;
		this.title = title;
		this.userId = userId; 
	}
	


	public Media(int id, String mediaType, String title, String content, String created_at, int userId) {
		this.ID = id;
		this.mediaType = mediaType;
		this.title = title;
		this.content = content;
		this.mediaDate = created_at;
		this.userId = userId; 
	}

	public int getId() {return ID;}
	public int getUserId() { return userId; }
	public String getMediaType() {return mediaType;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getMediaDate() {return mediaDate;}
}
