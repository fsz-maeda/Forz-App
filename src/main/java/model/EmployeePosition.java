package model;

public class EmployeePosition {
	private int employeeId;
	private String name;
	private String pass;
	private String mail;
	private String positionName;
	
	public EmployeePosition(int employeeId, String name, String pass, String mail, String positionName) {
		this.employeeId = employeeId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.positionName = positionName;
	}

	public int getEmployeeId() {
		return employeeId;
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
