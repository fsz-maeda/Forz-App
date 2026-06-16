package model;

import java.io.Serializable;
import java.util.Date;

public class MediaLikes implements Serializable{
	private int likesId;
	private int employeeId;
	private int mediaId;
	private Date createdAt;
	
	public MediaLikes(int likesId, int employeeId, int mediaId, Date createdAt) {
		this.likesId = likesId;
		this.employeeId = employeeId;
		this.mediaId = mediaId;
		this.createdAt = createdAt;
	}

	public int getLikesId() {
		return likesId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
}
