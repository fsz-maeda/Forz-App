package model;

public class UserPosition {
	private int userId;
	private String name;
	private String pass;
	private String mail;
	private String positionName;
	
	public UserPosition(int userId, String name, String pass, String mail, String positionName) {
		this.userId = userId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.positionName = positionName;
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

	public String getPositionName() {
		return positionName;
	}
}
