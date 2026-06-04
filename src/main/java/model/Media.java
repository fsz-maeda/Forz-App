package model;
import java.io.Serializable;

public class Media implements Serializable{

	private String mediaType;
	private String title;
	private String content;
	private String mediaDate;
	
	public Media(String mediaType,String title,String content,String mediaDate) {
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
	public String getMediaType() {return mediaType;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getMediaDate() {return mediaDate;}
}
