package model;

import java.io.Serializable;

public class User implements Serializable{
	private int userId;
	private String name;
	private String pass;
	private String mail;
	private int positionId;
	private int departmentId;
	
	public User(int userId, String name, String pass, String mail, int positionId) {
		this.userId = userId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.positionId = positionId;
	}
	
	public User(int userId, String name, String pass, String mail, int positionId, int departmentId) {
		this.userId = userId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.positionId = positionId;
		this.departmentId = departmentId;
	}

	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public String getMail() {
		return mail;
	}

	public int getPositionId() {
		return positionId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	
}
